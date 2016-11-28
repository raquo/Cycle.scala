package cycle.base

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait Source extends js.Object

@ScalaJSDefined
trait RawSource extends js.Object

@ScalaJSDefined
trait IsolatableSource[SelfSource <: IsolatableSource[_, Sink], Sink] extends Source {

  def isolateSource(source: SelfSource, scope: String): SelfSource

  def isolateSink(sink: Sink, scope: String): Sink
}

@ScalaJSDefined
trait Sources extends js.Object
