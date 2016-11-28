package cycle.xstream.run

import cycle.base._
import cycle.xstream.adapter.XStreamAdapter

import scala.scalajs.js

object XStreamRun {

  private val cycleOptions = new RawCycleOptions(XStreamAdapter.sharedAdapter)

  @inline
  def apply[Si <: Sink, So <: Source, Sis <: Sinks, Sos <: Sources](
    main: Sos => Sis,
    driver: Driver[Si, So, Sis, Sos]
  ): DisposeFunction = {
    cycleExecution(main, driver).run()
  }

  @inline def apply[
    Si1 <: Sink, So1 <: Source, Sis1 <: Sinks, Sos1 <: Sources,
    Si2 <: Sink, So2 <: Source, Sis2 <: Sinks, Sos2 <: Sources
  ](
    main: (Sos1 with Sos2) => (Sis1 with Sis2),
    driver1: Driver[Si1, So1, Sis1, Sos1],
    driver2: Driver[Si2, So2, Sis2, Sos2]
  ): DisposeFunction = {
    cycleExecution(main, driver1, driver2).run()
  }

  @inline def apply[
    Si1 <: Sink, So1 <: Source, Sis1 <: Sinks, Sos1 <: Sources,
    Si2 <: Sink, So2 <: Source, Sis2 <: Sinks, Sos2 <: Sources,
    Si3 <: Sink, So3 <: Source, Sis3 <: Sinks, Sos3 <: Sources
  ](
    main: (Sos1 with Sos2 with Sos3) => (Sis1 with Sis2 with Sis3),
    driver1: Driver[Si1, So1, Sis1, Sos1],
    driver2: Driver[Si2, So2, Sis2, Sos2],
    driver3: Driver[Si3, So3, Sis3, Sos3]
  ): DisposeFunction = {
    cycleExecution(main, driver1, driver2, driver3).run()
  }

  @inline def apply[
    Si1 <: Sink, So1 <: Source, Sis1 <: Sinks, Sos1 <: Sources,
    Si2 <: Sink, So2 <: Source, Sis2 <: Sinks, Sos2 <: Sources,
    Si3 <: Sink, So3 <: Source, Sis3 <: Sinks, Sos3 <: Sources,
    Si4 <: Sink, So4 <: Source, Sis4 <: Sinks, Sos4 <: Sources
  ](
    main: (Sos1 with Sos2 with Sos3 with Sos4) => (Sis1 with Sis2 with Sis3 with Sis4),
    driver1: Driver[Si1, So1, Sis1, Sos1],
    driver2: Driver[Si2, So2, Sis2, Sos2],
    driver3: Driver[Si3, So3, Sis3, Sos3],
    driver4: Driver[Si4, So4, Sis4, Sos4]
  ): DisposeFunction = {
    cycleExecution(main, driver1, driver2, driver3, driver4).run()
  }

  @inline
  private def cycleExecution[Sos <: Sources, Sis <: Sinks](
    main: Sos => Sis,
    drivers: Driver[_, _, _, _]*
  ): Execution[Sos, Sis] = {
    RawCycle(main, combineDrivers(drivers), cycleOptions)
  }

  private def combineDrivers(drivers: Seq[Driver[_, _, _, _]]): RawDrivers = {
    // @TODO[Integrity] asInstanceOf
    val rawDriverPairs = drivers
      .map(_.asInstanceOf[Driver[Sink, Source, Sinks, Sources]])
      .map(driver => (driver.key, js.Any.fromFunction3(driver.driverFunction)))

    js.Dictionary(rawDriverPairs: _*)
  }
}
