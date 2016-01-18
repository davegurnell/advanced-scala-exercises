import cats.Monoid

object SuperAdder {
  // Adding Ints directly:
  def add1(items: List[Int]): Int =
    items.foldLeft(0){ _ + _ }

  // Adding Ints using Monoids:
  def add2(items: List[Int]): Int = {
    import cats.std.int._
    import cats.syntax.semigroup._

    items.foldLeft(Monoid[Int].empty){ _ |+| _ }
  }

  // Adding anything that has a Monoid:
  def add3[A](items: List[A])(implicit monoid: Monoid[A]): A = {
    import cats.syntax.semigroup._

    items.foldLeft(monoid.empty){ _ |+| _ }
  }
}

object MonoidExample {
  def main(args: Array[String]) = {
    println(SuperAdder.add1(List(1, 2, 3, 4, 5)))

    println(SuperAdder.add2(List(1, 2, 3, 4, 5)))

    import cats.std.int._
    import cats.std.option._
    println(SuperAdder.add3(List(None, Some(1), Some(2), Some(3), Some(4), Some(5))))
  }
}