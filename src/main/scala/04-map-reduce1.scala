import scalaz.Monoid

object MapReduceExample1 {
  def foldMap[A, B](list: List[A])(func: A => B)(implicit monoid: Monoid[B]): B = {
    import scalaz.syntax.monoid._

    list.foldLeft(mzero[B])((accum, item) => accum |+| func(item))
  }

  def main(args: Array[String]) = {
    import scalaz.std.anyVal._
    println(foldMap(Common.wordList)(_.length))

    import scalaz.syntax.foldable._
    import scalaz.std.list._
    println(Common.wordList.foldMap(_.length))
  }
}
