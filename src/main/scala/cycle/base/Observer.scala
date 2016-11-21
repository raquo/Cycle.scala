package cycle.base

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait Observer[T] extends js.Object {

  def next(x: T): Unit

  def error[E](e: E): Unit

  def complete(c: T): Unit

  def complete(): Unit
}
