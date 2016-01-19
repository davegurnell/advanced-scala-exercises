import scala.language.higherKinds
import scalaz.Monad

object OrchestrationExample {
  // TODO:
  //  - Implement getAvatarForEmail concretely
  //    in terms of getAvatarUrl and getAvatar
  //
  //  - Uncomment RealAvatarService
  //    and add imports to make it compile
  //
  //  - Uncomment FakeAvatarService
  //    and add imports to make it compile

  final case class Avatar(url: String)

  trait AvatarService[F[_]] {
    implicit def monad: Monad[F]

    def getAvatarUrl(email: String): F[String]

    def getAvatar(url: String): F[Avatar]

    final def getAvatarForEmail(email: String): F[Avatar] =
      ???
  }

  // object RealAvatarService extends AvatarService[Future] {
  //   val monad = Monad[Future]
  //
  //   def getAvatarUrl(email: String): Future[String] =
  //     Future.successful(s"http://avatarstuff.com/$email")
  //
  //   def getAvatar(url: String): Future[Avatar] =
  //     Future.successful(Avatar(url))
  // }

  // println(Await.result(RealAvatarService.getAvatarForEmail("dave@example.com"), 3.seconds))

  // object FakeAvatarService extends AvatarService[Id] {
  //   val monad = Monad[Id]
  //
  //   def getAvatarUrl(email: String): String =
  //     s"http://avatarstuff.com/$email"
  //
  //   def getAvatar(url: String): Avatar =
  //     Avatar(url)
  // }

  // println(FakeAvatarService.getAvatarForEmail("dave@example.com"))

  def main(args: Array[String]) = ()
}
