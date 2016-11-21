package cycle.xstream.run

import cycle.base._
import cycle.xstream.adapter.XStreamAdapter

import scala.scalajs.js
import scala.scalajs.js.Dynamic.global
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

@ScalaJSDefined
trait RunConfig[TSources <: Sources, TSinks <: Sinks, TDrivers <: Drivers] extends js.Object {
  val main: TSources => TSinks
  val drivers: TDrivers
}

@ScalaJSDefined
class RunConfig_DOM(
  val main: Sources_DOM => Sinks_DOM,
  val drivers: Drivers_DOM
) extends RunConfig[Sources_DOM, Sinks_DOM, Drivers_DOM]

@ScalaJSDefined
class RunConfig_DOM_SourceOnly(
  val main: Sources_DOM => Sinks,
  val drivers: Drivers_DOM
) extends RunConfig[Sources_DOM, Sinks, Drivers_DOM]

object XStreamRun {
  def apply[TSources <: Sources, TSinks <: Sinks, TDrivers <: Drivers](
    config: RunConfig[TSources, TSinks, TDrivers]
  ): DisposeFunction = {
    val execution = Cycle[TSources, TSinks, TDrivers](
      main = config.main,
      drivers = config.drivers,
      options = new Options(new XStreamAdapter)
    )
    if (global.CyclejsDevTool_startGraphSerializer.isInstanceOf[js.Function]) {
      global.CyclejsDevTool_startGraphSerializer.asInstanceOf[js.Function1[TSinks, Unit]](execution.sinks)
    }
    execution.run()
  }
}
