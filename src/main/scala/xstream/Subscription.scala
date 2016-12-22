package xstream

import scala.scalajs.js

@js.native
trait Subscription[T] extends js.Object {

  // We do not expose these "private" vals to Scala
  // val _stream: RawStream[T] = js.native
  // val _listener: RawListener[T] = js.native

  def unsubscribe(): Unit = js.native
}
