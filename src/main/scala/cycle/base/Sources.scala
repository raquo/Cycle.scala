package cycle.base

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait RawSource extends js.Object

@ScalaJSDefined
trait IsolatableSource[So <: IsolatableSource[_, Si], Si] extends js.Object {

  protected def isolateSource(source: So, scope: String): So

  protected def isolateSink(sink: Si, scope: String): Si
}

@js.native
trait Sources extends js.Object
