package cycle.base

import xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Subject[T](
  val stream: XStream[T],
  val observer: Observer[T]
) extends js.Object
