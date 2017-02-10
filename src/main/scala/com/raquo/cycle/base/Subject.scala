package com.raquo.cycle.base

import com.raquo.xstream.EStream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Subject[T, E <: Exception](
  val stream: EStream[T, E],
  val observer: Observer[T, E]
) extends js.Object
