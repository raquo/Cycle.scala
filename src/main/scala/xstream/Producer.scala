package xstream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

// @TODO this pattern is weird. There must be a better way. See examples in Scala.js 0.14 release notes.

@ScalaJSDefined
trait Producer[T] extends js.Object {

  def start: js.Function1[Listener[T], Unit]

  def stop: js.Function0[Unit]
}

object Producer {

  def apply[T](
    onStart: Listener[T] => Unit,
    onStop: () => Unit
  ): Producer[T] = new Producer[T] {
    override def start: js.Function1[Listener[T], Unit] = onStart
    override def stop: js.Function0[Unit] = onStop
  }
}
