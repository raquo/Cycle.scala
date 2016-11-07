package cycle.xstreamrun

import cycle.base.{Drivers, Drivers_DOM, Sinks, Sinks_DOM, Sources, Sources_DOM}

import scala.scalajs.js
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

@ScalaJSDefined
abstract class Main(sources: Sources) extends Sinks

@ScalaJSDefined
abstract class Main_DOM(sources: Sources_DOM) extends Sinks_DOM

@js.native
@JSImport("@cycle/xstream-run", JSImport.Namespace)
object RawXStreamRun extends js.Object {
  type DisposeFunction = js.Function0[Unit]

  def run[TSources <: Sources, TSinks <: Sinks](
    main: js.Function1[TSources, TSinks],
    drivers: Drivers
  ): DisposeFunction = js.native
}

object XStreamRun {
  def run[TSources <: Sources, TSinks <: Sinks, TDrivers <: Drivers](
    config: RunConfig[TSources, TSinks, TDrivers]
  ): RawXStreamRun.DisposeFunction = {
    RawXStreamRun.run[TSources, TSinks](config.main, config.drivers)
  }
}
