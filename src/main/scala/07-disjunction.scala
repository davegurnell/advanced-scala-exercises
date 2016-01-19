import scala.language.higherKinds
import scalaz.\/
import scalaz.Monad
import scalaz.syntax.either._

object DisjunctionExample {
  case class User(username: String, password: String)

  val users = List(
    User("alice"   , "god"),
    User("bob"     , "love"),
    User("charlie" , "sex"),
    User("dan"     , "secret"))

  type MightFail[A] = String \/ A

  def readString(args: Array[String], num: Int): MightFail[String] =
    if(args.length > num) {
      args(num).right
    } else {
      s"Command line argument $num not found".left
    }

  def fetchUser(username: String): MightFail[User] =
    users.find(_.username == username) match {
      case Some(user) => user.right
      case None       => "User not found".left
    }

  def checkPassword(user: User, password: String): MightFail[User] =
    Option(user)
      .filter(_.password == password)
      .map(_.right[String])
      .getOrElse("Password incorrect".left)

  def main(args: Array[String]): Unit = {
    val user = for {
      username <- readString(args, 0)
      password <- readString(args, 1)
      unauthed <- fetchUser(username)
      authed   <- checkPassword(unauthed, password)
    } yield authed

    println(user)
  }
}
