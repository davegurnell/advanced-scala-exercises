import scala.language.higherKinds

object FlatMapExample {
  // TODO:
  //  - Here's a rough approximation of Scalaz' definition of Monad
  //  - Implement map and join in terms of flatMap and point

  trait Monad[F[_]] {
    // Abstract methods:
    def point[A](value: A): F[A]
    def flatMap[A, B](input: F[A])(func: A => F[B]): F[B]

    // Concrete methods defined in terms of the above"
    def map[A, B](input: F[A])(func: A => B): F[B] = ???
    def join[A](input: F[F[A]]): F[A] = ???
  }

  def main(args: Array[String]): Unit = ()
}
