package cycle.xstreamrun

import cycle.base.{DisposeFunction, Drivers, Sinks, Sources}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("@cycle/xstream-run", JSImport.Namespace)
object XStreamRun extends js.Object {
  def run[TSources <: Sources, TSinks <: Sinks](
    main: js.Function1[TSources, TSinks],
    drivers: Drivers
  ): DisposeFunction = js.native
}
