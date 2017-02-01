package com.raquo.cycle.base

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait Observer[T, EE <: Exception] extends js.Object {

  def next(x: T): Unit

  def error(e: EE): Unit

  def error(e: Exception | js.Error): Unit

  def complete(c: T): Unit

  def complete(): Unit
}
