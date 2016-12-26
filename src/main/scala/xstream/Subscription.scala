package xstream

import scala.scalajs.js

@js.native
trait Subscription[+T] extends js.Object {

  // We do not expose these "private" vals to Scala
  // val _stream: XStream[T] = js.native
  // val _listener: Listener[T] = js.native

  def unsubscribe(): Unit = js.native
}
