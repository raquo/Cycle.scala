package cycle.base

import cycle.dom.RawDOMSource

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait Source extends js.Object

@ScalaJSDefined
trait IsolatableSource[SelfSource <: IsolatableSource[_, Sink], Sink] extends Source {

  def isolateSource(source: SelfSource, scope: String): SelfSource

  def isolateSink(sink: Sink, scope: String): Sink
}

@js.native
trait Sources extends js.Object

@js.native
class Sources_DOM() extends Sources {
  val DOM: RawDOMSource = js.native
}
