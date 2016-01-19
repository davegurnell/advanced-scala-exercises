object ShowExample {

  // The type class -----------------------------

  trait Show[A] {
    def stringify(value: A): String
  }

  // Standard type class instances --------------

  // TODO:
  //  - You'll need to define some type class instances
  //    to finish the exercises below

  // Type class interface -----------------------

  object Show {
    def apply[A](implicit showInstance: Show[A]): Show[A] =
      showInstance
  }

  // Extra syntax (optional) --------------------

  implicit class ShowOps[A](value: A) {
    def stringify(implicit showInstance: Show[A]): String =
      showInstance.stringify(value)

    def print(implicit showInstance: Show[A]): Unit =
      println(value.stringify)
  }

  // Demonstration:

  def main(args: Array[String]) = {
    // TODO:
    //  - Print a String to the console
    //    by explicitly summoning an instance of Show
    //    and using its stringify method

    //  - Print a List of Strings to the console
    //    using the syntax provided in ShowOps

    //  - Print a List of Options of Strings to the console
    //    using any method you like
  }

}
