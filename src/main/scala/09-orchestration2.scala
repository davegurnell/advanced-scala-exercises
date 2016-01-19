import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future
import scala.language.higherKinds
import scalaz.Id._
import scalaz.Monad
import scalaz.std.scalaFuture._
import scalaz.syntax.monad._

object OrchestrationExample2 {
  final case class Avatar(url: String)

  // TODO:
  //  - Modify RealAvatarService so that:
  //     - getAvatarUrl fails if the email doesn't contain "dave"
  //     - getAvatar fails if the url doesn't contain "example.com"

  trait AvatarService[F[_]] {
    implicit def monad: Monad[F]

    def getAvatarUrl(email: String): F[String]

    def getAvatar(url: String): F[Avatar]

    final def getAvatarForEmail(email: String): F[Avatar] = for {
      person <- getAvatarUrl(email)
      avatar <- getAvatar(person)
    } yield avatar
  }

  object RealAvatarService extends AvatarService[Future] {
    val monad = Monad[Future]

    def getAvatarUrl(email: String): Future[String] =
      Future.successful(s"http://avatarstuff.com/$email")

    def getAvatar(url: String): Future[Avatar] =
      Future.successful(Avatar(url))
  }

  object FakeAvatarService extends AvatarService[Id] {
    val monad = Monad[Id]

    def getAvatarUrl(email: String): String =
      s"http://avatarstuff.com/$email"

    def getAvatar(url: String): Avatar =
      Avatar(url)
  }

  def main(args: Array[String]) = {
    println(Await.result(RealAvatarService.getAvatarForEmail("dave@example.com"), 3.seconds))

    println(FakeAvatarService.getAvatarForEmail("dave@example.com"))
  }
}
