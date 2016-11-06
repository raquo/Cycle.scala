package xstream

import scala.scalajs.js

@js.native
trait InternalProducer[T] extends js.Object {
  def _start(listener: InternalListener[T]): Unit = js.native
  def _stop(): Unit = js.native
}

@js.native
trait InternalListener[T] extends js.Object {
  def _n(v: T): Unit = js.native
  def _e[E](err: E): Unit = js.native
  def _c(): Unit = js.native
}

@js.native
trait Listener[T] extends js.Object {
  def next(x: T): Unit = js.native
  def error[E](err: E): Unit = js.native
  def complete(): Unit = js.native
}

@js.native
trait PartialListener[T] extends Listener[T] {
  // @TODO a Listener where next/error/complete methods are all optional
}

@js.native
class Subscription[T](private val _stream: XStream[T], private val _listener: Listener[T]) extends js.Object {
  def unsubscribe(): Unit = js.native
}

@js.native
trait Producer[T] extends js.Object {
  def start(listener: Listener[T]): Unit = js.native
  def stop(): Unit = js.native
}
