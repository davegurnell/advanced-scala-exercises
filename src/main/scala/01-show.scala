object ShowExample {

  // The type class -----------------------------

  trait Show[A] extends (A => String)

  // Standard type class instances --------------

  // TODO:
  //  - Define an instance of Show for String that preserves the quotes
  //  - Define an instance of Show for List that looks like a JS array

  // Type class interface -----------------------

  object Show {
    def apply[A](implicit showInstance: Show[A]): Show[A] =
      showInstance
  }

  // Extra syntax (optional) --------------------

  implicit class ShowOps[A](value: A) {
    def stringify(implicit showInstance: Show[A]): String =
      showInstance(value)

    def print(implicit showInstance: Show[A]): Unit =
      println(value.stringify)
  }

  // Demonstration:

  def main(args: Array[String]) = {
    // TODO:
    //  - Print a String to the console
    //    by explicitly summoning an instance of Show
    //    and using its apply method
    //  - Print a List of Strings to the console
    //    using the syntax provided in ShowOps
    //  - Print a List of Options of Strings to the console
    //    using any method you like
  }

}
