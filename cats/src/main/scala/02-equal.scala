object EqualExample {
  def example1() = {
    import cats.Eq
    import cats.std.int._

    println("Example 1")
    println(Eq[Int].eqv(1, 1))
    println(Eq[Int].eqv(1, 2))
  }

  def example2() = {
    import cats.Eq
    import cats.std.int._
    import cats.syntax.eq._

    println("Example 2")
    println(1 === 1)
    println(1 === 2)
  }

  def example3() = {
    import cats.Eq
    import cats.std.int._
    import cats.std.list._
    import cats.syntax.eq._

    println("Example 3")
    println(List(1, 2, 3) === List(1, 2, 3))
    println(List(1, 2, 3) === List(3, 2, 1))
  }

  def example4() = {
    import cats.Eq
    import cats.std.int._
    import cats.std.option._
    import cats.syntax.eq._

    println("Example 4")
    println(Option(1) === Option(1))
    println(Option(1) === Option.empty)
  }

  def main(args: Array[String]): Unit = {
    example1()
    example2()
    example3()
    example4()
  }
}