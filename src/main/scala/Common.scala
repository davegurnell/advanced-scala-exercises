object Common {
  private def wordSource = scala.io.Source.fromFile("/usr/share/dict/words")

  def wordList: List[String] =
    wordSource.getLines.toList

  def wordStream: Stream[String] =
    wordSource.getLines.toStream
}
