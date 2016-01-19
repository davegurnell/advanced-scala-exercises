import scalaz.Validation

object ValidationExample {
  // TODO:
  //  - Implement greaterThan, nonEmpty, and validateAddress
  //  - Ensure nonEmpty trims the string supplied as an input

  type Validated[A] = Validation[List[String], A]

  case class Address(house: Int, street: String)

  def greaterThan(num: Int, min: Int): Validated[Int] =
    ???

  def nonEmpty(str: String): Validated[String] =
    ???

  def validateAddress(address: Address): Validated[Address] =
    ???

  def main(args: Array[String]): Unit = {
    println(validateAddress(Address(29, "   Acacia Road   ")))
    println(validateAddress(Address(29, "   ")))
    println(validateAddress(Address(-1, "")))
  }
}
