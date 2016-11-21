package xstream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait RawListener[T] extends js.Object {

  def next(x: T): Unit

  def error[E](e: E): Unit

  def complete(): Unit
}

@js.native
trait RawSubscription[T] extends js.Object {

  // We do not expose these "private" vals to Scala
  // val _stream: RawStream[T] = js.native
  // val _listener: RawListener[T] = js.native

  def unsubscribe(): Unit = js.native
}

@ScalaJSDefined
trait RawProducer[T] extends js.Object {

  def start(listener: RawListener[T]): Unit

  def stop(): Unit
}
