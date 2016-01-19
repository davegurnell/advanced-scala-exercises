import scala.language.higherKinds

object FunctorExample {
  // trait Functor[F[_]] {
  //   def map[A, B](input: F[A])(func: A => B): F[B]
  // }

  // implicit val listFunctor = new Functor[List] {
  //   def map[A, B](input: List[A])(func: A => B): List[B] =
  //     input.map(func)
  // }

  // implicit val optionFunctor = new Functor[Option] {
  //   def map[A, B](input: Option[A])(func: A => B): Option[B] =
  //     input.map(func)
  // }

  import scalaz.Functor

  def addOneEverywhere[F[_]](input: F[Int])(implicit functor: Functor[F]): F[Int] = {
    import scalaz.syntax.functor._

    input.map(x => x + 1)
  }

  def main(args: Array[String]) = {
    import scalaz.std.option._
    println(addOneEverywhere(Option(1)))

    import scalaz.std.list._
    println(addOneEverywhere(List(1, 2, 3, 4, 5)))

    import scalaz.std.stream._
    println(addOneEverywhere(Stream(1, 2, 3, 4, 5)))
  }
}
