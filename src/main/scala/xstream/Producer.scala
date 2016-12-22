package xstream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

// @TODO this pattern is weird. There must be a better way. See examples in Scala.js 0.14 release notes.

@ScalaJSDefined
trait Producer[T] extends js.Object {

  def start(listener: Listener[T]): Unit

  def stop(): Unit
}

object Producer {

  def apply[T](start: Listener[T] => Unit, stop: () => Unit): Producer[T] =
    new RichProducer[T](start, stop)
}


@ScalaJSDefined
/** Note: names `_start` and `_stop` are used internally by cycle.js, do not create members with such names */
class RichProducer[T] private[xstream] (__start: Listener[T] => Unit, __stop: () => Unit) extends Producer[T] {

  override def start(rawListener: Listener[T]): Unit = __start(Listener.fromRawListener(rawListener))

  override def stop(): Unit = __stop()
}
