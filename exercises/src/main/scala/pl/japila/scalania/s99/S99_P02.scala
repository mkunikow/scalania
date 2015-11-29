package pl.japila.scalania.s99

object S99_P02 {
  def penultimate[T](ts: Seq[T]): Option[T] = {
    if (ts.size > 1) {
      ts.takeRight(2).headOption
    } else {
      None
    }
  }
}
