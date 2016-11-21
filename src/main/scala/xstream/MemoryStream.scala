package xstream

class MemoryStream[T](rawStream: RawMemoryStream[T]) extends XStream[T](rawStream) {

  override def map[U](project: T => U): MemoryStream[U] =
    new MemoryStream(rawStream.map(project))

  override def mapTo[U](projectedValue: U): MemoryStream[U] =
    new MemoryStream(rawStream.mapTo(projectedValue))

  override def take(amount: Int): MemoryStream[T] =
    new MemoryStream(rawStream.take(amount))

  override def endWhen[U](other: XStream[U]): MemoryStream[T] =
    new MemoryStream(rawStream.endWhen(other.rawStream))

  override def replaceError[E](replace: E => XStream[T]): MemoryStream[T] =
    new MemoryStream(rawStream.replaceError((error: E) => replace(error).rawStream))

  override def debug(spy: T => Unit): MemoryStream[T] =
    new MemoryStream(rawStream.debug(spy))

  override def debug(label: String): MemoryStream[T] =
    new MemoryStream(rawStream.debug(label))

  override def debug(): MemoryStream[T] =
    new MemoryStream(rawStream.debug())
}
