package cycle.base

import cycle.xstream.adapter.XStreamAdapter

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

@ScalaJSDefined
class RawCycleOptions (
  val streamAdapter: XStreamAdapter
) extends js.Object


@js.native
@JSImport("@cycle/base", JSImport.Default)
object RawCycle extends js.Object {
  def apply[Sos <: Sources, Sis <: Sinks](
    main: js.Function1[Sos, Sis],
    drivers: RawDrivers,
    options: RawCycleOptions
  ): Execution[Sos, Sis] = js.native
}
