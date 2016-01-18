object ShowExample {

  // The type class:

  trait Show[A] extends (A => String)

  // Standard type class instances:

  implicit val stringShow = new Show[String] {
    def apply(value: String) = "\"" + value.replaceAll("[\"]", "\\\"") + "\""
  }

  implicit val intShow = new Show[Int] {
    def apply(value: Int) = value.toString
  }

  // Type class interface

  object Show {
    def apply[A](implicit showInstance: Show[A]): Show[A] =
      showInstance
  }

  // Extra syntax (optional)

  implicit class ShowOps[A](value: A) {
    def stringify(implicit showInstance: Show[A]): String =
      showInstance(value)

    def print(implicit showInstance: Show[A]): Unit =
      println(value.stringify)
  }

  // Custom type class instances:

  case class Person(name: String, age: Int)

  implicit val personShow = new Show[Person] {
    def apply(person: Person) = {
      // We need three things in scope here:
      //   - ShowOps;
      //   - a Show instance for String;
      //   - a Show instance for Int;
      val name = person.name.stringify
      val age  = person.age.stringify
      s"Person($name, $age)"
    }
  }

  // Demonstration:

  def main(args: Array[String]) = {
    // We need two things in scope here:
    //   - ShowOps;
    //   - a Show instance for Person.
    Person("Dave", 37).print
  }

}
