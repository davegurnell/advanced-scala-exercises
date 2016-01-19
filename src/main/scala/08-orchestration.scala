import scala.language.higherKinds
import scalaz.Monad
import scalaz.Monoid

object OrchestrationExample {
  final case class Avatar(url: String)

  sealed trait AvatarService[F[_]] {
    implicit def monad: Monad[F]

    def getAvatarUrl(email: String): F[String]

    def getAvatar(url: String): F[Avatar]

    final def getAvatarForEmail(email: String): F[Avatar] = {
      import scalaz.syntax.monad._

      for {
        person <- getAvatarUrl(email)
        avatar <- getAvatar(person)
      } yield avatar
    }
  }

  import scala.concurrent.Future
  import scalaz.std.scalaFuture._
  import scala.concurrent.ExecutionContext.Implicits._
  final case object RealAvatarService extends AvatarService[Future] {
    val monad = Monad[Future]

    def getAvatarUrl(email: String): Future[String] =
      Future.successful(s"http://avatarstuff.com/$email")

    def getAvatar(url: String): Future[Avatar] =
      Future.successful(Avatar(url))
  }

  import scalaz.Id._
  final case object FakeAvatarService extends AvatarService[Id] {
    val monad = Monad[Id]

    def getAvatarUrl(email: String): String =
      s"http://avatarstuff.com/$email"

    def getAvatar(url: String): Avatar =
      Avatar(url)
  }

  def main(args: Array[String]) = {
    import scala.concurrent.Await
    import scala.concurrent.duration._
    println(Await.result(RealAvatarService.getAvatarForEmail("dave@example.com"), 3.seconds))

    println(FakeAvatarService.getAvatarForEmail("dave@example.com"))
  }
}
