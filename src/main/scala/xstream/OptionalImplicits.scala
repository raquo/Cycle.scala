package xstream

object OptionalImplicits {

  implicit class ShamefulStream[+T](val shamelessStream: XStream[T]) extends AnyVal {

    def shamefullySendNext[U >: T](value: U): Unit = shamelessStream.shamefullySendNext(value)

    def shamefullySendError[E](error: E): Unit = shamelessStream.shamefullySendError(error)

    def shamefullySendComplete(): Unit = shamelessStream.shamefullySendComplete()
  }
}
