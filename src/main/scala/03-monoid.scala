import scalaz.Monoid

object SuperAdder {
  // Adding Ints directly:
  def add1(items: List[Int]): Int =
    items.foldLeft(0){ _ + _ }

  // Adding Ints using Monoids:
  def add2(items: List[Int]): Int = {
    import scalaz.std.anyVal._
    import scalaz.syntax.monoid._

    items.foldLeft(mzero[Int]){ _ |+| _ }
  }

  // Adding anything that has a Monoid:
  def add3[A](items: List[A])(implicit monoid: Monoid[A]): A = {
    import scalaz.syntax.monoid._

    items.foldLeft(mzero){ _ |+| _ }
  }
}

object MonoidExample {
  def main(args: Array[String]) = {
    println(SuperAdder.add1(List(1, 2, 3, 4, 5)))

    println(SuperAdder.add2(List(1, 2, 3, 4, 5)))

    import scalaz.std.anyVal._
    println(SuperAdder.add3(List(1, 2, 3, 4, 5)))

    import scalaz.std.string._
    println(SuperAdder.add3(List("1", "2", "3", "4", "5")))

    import scalaz.std.option._
    println(SuperAdder.add3(List(None, Some(1), Some(2), Some(3), Some(4), Some(5))))

    import scalaz.Tags.Multiplication
    println(SuperAdder.add3(List(1, 2, 3, 4, 5).map(Multiplication.apply)))
  }
}