package cycle.base

import snabbdom.VNode
import xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait Sinks extends js.Object

@ScalaJSDefined
trait Sinks_DOM extends Sinks {
  val DOM: XStream[VNode]
}
