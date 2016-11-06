package cycle.base

import snabbdom.VNode
import xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait Sinks extends js.Object

@ScalaJSDefined
class Sinks_DOM(val DOM: XStream[VNode]) extends Sinks {}
