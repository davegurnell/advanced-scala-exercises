import scalaz.Monoid
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.duration._

object MapReduceExample {
  def foldMap[A, B](values: Iterable[A])(func: A => B)(implicit monoid: Monoid[B]): B = {
    import scalaz.syntax.monoid._

    values.foldLeft(mzero[B])((accum, item) => accum |+| func(item))
  }

  def parallelFoldMap[A, B](values: Iterable[A])(func: A => B)(implicit monoid: Monoid[B], ec: ExecutionContext): Future[B] = {
    val numCores: Int = Runtime.getRuntime.availableProcessors
    val groupSize: Int = (1.0 * values.size / numCores).ceil.toInt

    val groups: Iterator[Iterable[A]] =
      values.grouped(groupSize)

    val groupTotalFutures: Iterator[Future[B]] =
      groups.map(group => Future(foldMap(group)(func)))

    val grandTotalFuture: Future[B] =
      Future.sequence(groupTotalFutures).map(results => foldMap(results.toIterable)(identity))

    grandTotalFuture
  }

  def main(args: Array[String]) = {
    import scalaz.std.anyVal._
    println(Common.time(foldMap(Common.wordList)(_.length)))

    import scalaz.syntax.foldable._
    import scalaz.std.list._
    println(Common.time(Common.wordList.foldMap(_.length)))

    import scalaz.std.list._
    println(Common.time(Await.result(parallelFoldMap(Common.wordList)(_.length), 3.seconds)))
  }
}
