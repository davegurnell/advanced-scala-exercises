object Common {
  private def wordSource = scala.io.Source.fromFile("/usr/share/dict/words")

  def wordList: List[String] =
    wordSource.getLines.toList

  def wordStream: Stream[String] =
    wordSource.getLines.toStream

  def time[A](code: => A): (A, Long) = {
    val a = System.currentTimeMillis
    val r = code
    val b = System.currentTimeMillis
    (r, b - a)
  }

  def numCores: Int =
    Runtime.getRuntime.availableProcessors

  def groupSize[A](values: List[A]): Int =
    (1.0 * values.length / numCores).ceil.toInt
}
