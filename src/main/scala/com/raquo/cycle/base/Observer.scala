package com.raquo.cycle.base

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait Observer[T, E <: js.Error] extends js.Object {

  def next(x: T): Unit

  def error(e: E): Unit

  def complete(c: T): Unit

  def complete(): Unit
}
