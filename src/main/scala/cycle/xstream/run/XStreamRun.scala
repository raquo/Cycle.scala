package cycle.xstream.run

import cycle.base._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("@cycle/xstream-run", JSImport.Namespace)
object RawXStreamRun extends js.Object {
  def run[Sos <: Sources, Sis <: Sinks](
    main: js.Function1[Sos, Sis],
    drivers: RawDrivers
  ): DisposeFunction = js.native
}

object XStreamRun {

  @inline
  def apply[Sis <: Sinks, Sos <: Sources](
    main: Sos => Sis,
    driver: Driver[_, _, Sis, Sos]
  ): DisposeFunction = {
    run(main, driver)
  }

  @inline def apply[
    Sis1 <: Sinks, Sos1 <: Sources,
    Sis2 <: Sinks, Sos2 <: Sources
  ](
    main: (Sos1 with Sos2) => (Sis1 with Sis2),
    driver1: Driver[_, _, Sis1, Sos1],
    driver2: Driver[_, _, Sis2, Sos2]
  ): DisposeFunction = {
    run(main, driver1, driver2)
  }

  @inline def apply[
    Sis1 <: Sinks, Sos1 <: Sources,
    Sis2 <: Sinks, Sos2 <: Sources,
    Sis3 <: Sinks, Sos3 <: Sources
  ](
    main: (Sos1 with Sos2 with Sos3) => (Sis1 with Sis2 with Sis3),
    driver1: Driver[_, _, Sis1, Sos1],
    driver2: Driver[_, _, Sis2, Sos2],
    driver3: Driver[_, _, Sis3, Sos3]
  ): DisposeFunction = {
    run(main, driver1, driver2, driver3)
  }

  @inline def apply[
    Sis1 <: Sinks, Sos1 <: Sources,
    Sis2 <: Sinks, Sos2 <: Sources,
    Sis3 <: Sinks, Sos3 <: Sources,
    Sis4 <: Sinks, Sos4 <: Sources
  ](
    main: (Sos1 with Sos2 with Sos3 with Sos4) => (Sis1 with Sis2 with Sis3 with Sis4),
    driver1: Driver[_, _, Sis1, Sos1],
    driver2: Driver[_, _, Sis2, Sos2],
    driver3: Driver[_, _, Sis3, Sos3],
    driver4: Driver[_, _, Sis4, Sos4]
  ): DisposeFunction = {
    run(main, driver1, driver2, driver3, driver4)
  }

  @inline private def run[Sos <: Sources, Sis <: Sinks](
    main: Sos => Sis,
    drivers: Driver[_, _, _, _]*
  ): DisposeFunction = {
    RawXStreamRun.run(main, combineDrivers(drivers))
  }

  private def combineDrivers(drivers: Seq[Driver[_, _, _, _]]): RawDrivers = {
    val rawDriverPairs = drivers.map(driver =>
      (driver.key, js.Any.fromFunction3(driver.driverFunction))
    )

    js.Dictionary(rawDriverPairs: _*)
  }
}
