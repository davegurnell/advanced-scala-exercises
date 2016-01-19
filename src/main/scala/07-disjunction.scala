import scalaz.\/

object DisjunctionExample {
  case class User(username: String, password: String)

  val users = List(
    User("alice"   , "god"),
    User("bob"     , "love"),
    User("charlie" , "sex"),
    User("dan"     , "secret"))

  type MightFail[A] = String \/ A

  // TODO:
  //  - Implement the three methods below:
  //     - readString succeeds if the args array is long enough
  //     - fetchUser succeeds if the user is in the list
  //     - checkPassword succeeds if the password is correct
  //  - Implement the for-comprehension below

  def readString(args: Array[String], num: Int): MightFail[String] =
    ???

  def fetchUser(username: String): MightFail[User] =
    ???

  def checkPassword(user: User, password: String): MightFail[User] =
    ???

  def main(args: Array[String]): Unit = {
    // println(for {
    //   username <- readString(args, 0)
    //   password <- readString(args, 1)
    //   unauthed <- fetchUser(username)
    //   authed   <- checkPassword(unauthed, password)
    // } yield authed)
  }
}
