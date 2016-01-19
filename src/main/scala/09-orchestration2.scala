import scala.language.higherKinds
import scalaz.Monad
import scalaz.Monoid

object OrchestrationExample2 {
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

  import scalaz.\/
  import scalaz.EitherT
  import scala.concurrent.Future
  type FutureFallable[A] = EitherT[Future, String, A]

  import scalaz.std.scalaFuture._
  import scala.concurrent.ExecutionContext.Implicits._
  final case object RealAvatarService extends AvatarService[FutureFallable] {
    val monad = Monad[FutureFallable]

    def getAvatarUrl(email: String): FutureFallable[String] =
      if(email contains "example.com") {
        EitherT.right(Future.successful(s"http://avatarstuff.com/$email"))
      } else {
        EitherT.left(Future.successful("Email not found"))
      }

    def getAvatar(url: String): FutureFallable[Avatar] =
      if(url contains "dave") {
        EitherT.right(Future.successful(Avatar(url)))
      } else {
        EitherT.left(Future.successful("Avatar not found"))
      }
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
    println(Await.result(RealAvatarService.getAvatarForEmail("dave@example.com").run, 3.seconds))

    println(FakeAvatarService.getAvatarForEmail("dave@example.com"))
  }
}
