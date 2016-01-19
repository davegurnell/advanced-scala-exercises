import scalaz.Validation
import scalaz.syntax.applicative._
import scalaz.syntax.validation._
import scalaz.std.list._

object ValidationExample {
  case class Address(house: Int, street: String)

  type Validated[A] = Validation[List[String], A]

  def greaterThan(num: Int, min: Int): Validated[Int] =
    if(num > min) num.success else List(s"$num must be > $min").failure

  def nonEmpty(str: String): Validated[String] = {
    val trimmed = str.trim
    if(trimmed.isEmpty) List(s"'$trimmed' must be non-empty").failure else trimmed.success
  }

  def validateAddress(address: Address): Validated[Address] =
    (greaterThan(address.house, 0) |@| nonEmpty(address.street))(Address.apply)

  def main(args: Array[String]): Unit = {
    println(validateAddress(Address(29, "   Acacia Road   ")))
    println(validateAddress(Address(29, "   ")))
    println(validateAddress(Address(-1, "")))
  }
}
