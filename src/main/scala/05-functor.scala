import scala.language.higherKinds

object FunctorExample {
  // TODO:
  //  - Define your own Functor type class
  //  - Define instances for List and Option
  //  - Use these definitions to write an `addOneEverywhere` method
  //    that accepts an `F[Int]` (for some arbitrary `F`) and adds 1 to the contents

  // println(addOneEverywhere(Option(1)))
  // println(addOneEverywhere(List(1, 2, 3, 4, 5)))

  // TODO:
  //  - Replace your definition of Functor with Scalaz' definition
  //  - Make everything compile again
  //  - Use Scalaz to add 1 to the numbers in this Stream:

  // println(addOneEverywhere(Stream(1, 2, 3, 4, 5)))

  def main(args: Array[String]) = ()
}
