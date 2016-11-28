package cycle.base

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait Source extends js.Object

@ScalaJSDefined
trait RawSource extends js.Object

@ScalaJSDefined
trait IsolatableSource[So <: IsolatableSource[_, Si], Si] extends js.Object {

  def isolateSource(source: So, scope: String): So

  def isolateSink(sink: Si, scope: String): Si
}

@ScalaJSDefined
trait Sources extends js.Object
