object EqualExample {
  def example1() = {
    import scalaz.Equal
    import scalaz.std.anyVal._

    println("Example 1")
    println(Equal[Int].equal(1, 1))
    println(Equal[Int].equal(1, 2))
  }

  def example2() = {
    import scalaz.Equal
    import scalaz.std.anyVal._
    import scalaz.syntax.equal._

    println("Example 2")
    println(1 === 1)
    println(1 === 2)
  }

  def example3() = {
    import scalaz.Equal
    import scalaz.std.anyVal._
    import scalaz.std.list._
    import scalaz.syntax.equal._

    println("Example 3")
    println(List(1, 2, 3) === List(1, 2, 3))
    println(List(1, 2, 3) === List(3, 2, 1))
  }

  def example4() = {
    import scalaz.Equal
    import scalaz.std.anyVal._
    import scalaz.std.option._
    import scalaz.syntax.equal._

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