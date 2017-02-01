package com.raquo.cycle.base

import com.raquo.xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Subject[T, E <: Exception](
  val stream: XStream[T, E],
  val observer: Observer[T, E]
) extends js.Object
