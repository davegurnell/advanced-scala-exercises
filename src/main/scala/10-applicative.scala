import scala.language.higherKinds
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.duration._
import scalaz.std.scalaFuture._

object ApplicativeExample {
  // TODO:
  //  - Implement parallelTotal using applicative syntax
  //  - Check it runs faster than sequentialTotal

  def getTraffic(hostname: String): Future[Int] = Future {
    Thread.sleep(1000)
    (hostname.## % 1000).toInt
  }

  val sequentialTotal: Future[Int] = for {
    a <- getTraffic("host1")
    b <- getTraffic("host2")
    c <- getTraffic("host3")
    d <- getTraffic("host4")
  } yield a + b + c + d

  val parallelTotal: Future[Int] = ???

  println(Await.result(parallelTotal, 2.seconds))
  println(Await.result(sequentialTotal, 2.seconds))

  def main(args: Array[String]): Unit = ()
}
