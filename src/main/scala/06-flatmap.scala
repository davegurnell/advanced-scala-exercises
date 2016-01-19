import scala.language.higherKinds

object FlatMapExample {
  trait Monad[F[_]] {
    def point[A](value: A): F[A]

    def flatMap[A, B](input: F[A])(func: A => F[B]): F[B]

    def map[A, B](input: F[A])(func: A => B): F[B] =
      flatMap(input)(value => point(func(value)))

    def join[A](input: F[F[A]]): F[A] =
      flatMap(input)(identity)
  }
}
