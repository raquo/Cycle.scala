package cycle.xstream.run

import cycle.base._
import cycle.xstream.adapter.XStreamAdapter

import scala.scalajs.js

object XStreamRun {

  private val cycleOptions = new RawCycleOptions(XStreamAdapter.sharedAdapter)

  @inline
  def apply[Si <: Sink, RawSi <: RawSink, So <: Source, RawSo <: RawSource, Sis <: Sinks, Sos <: Sources](
    main: Sos => Sis,
    driver: Driver[Si, RawSi, So, RawSo, Sis, Sos]
  ): DisposeFunction = {
    cycleExecution(main, driver).run()
  }

  @inline def apply[
    Si1 <: Sink, RawSi1 <: RawSink, So1 <: Source, RawSo1 <: RawSource, Sis1 <: Sinks, Sos1 <: Sources,
    Si2 <: Sink, RawSi2 <: RawSink, So2 <: Source, RawSo2 <: RawSource, Sis2 <: Sinks, Sos2 <: Sources
  ](
    main: (Sos1 with Sos2) => (Sis1 with Sis2),
    driver1: Driver[Si1, RawSi1, So1, RawSo1, Sis1, Sos1],
    driver2: Driver[Si2, RawSi2, So2, RawSo2, Sis2, Sos2]
  ): DisposeFunction = {
    cycleExecution(main, driver1, driver2).run()
  }

  @inline def apply[
    Si1 <: Sink, RawSi1 <: RawSink, So1 <: Source, RawSo1 <: RawSource, Sis1 <: Sinks, Sos1 <: Sources,
    Si2 <: Sink, RawSi2 <: RawSink, So2 <: Source, RawSo2 <: RawSource, Sis2 <: Sinks, Sos2 <: Sources,
    Si3 <: Sink, RawSi3 <: RawSink, So3 <: Source, RawSo3 <: RawSource, Sis3 <: Sinks, Sos3 <: Sources
  ](
    main: (Sos1 with Sos2 with Sos3) => (Sis1 with Sis2 with Sis3),
    driver1: Driver[Si1, RawSi1, So1, RawSo1, Sis1, Sos1],
    driver2: Driver[Si2, RawSi2, So2, RawSo2, Sis2, Sos2],
    driver3: Driver[Si3, RawSi3, So3, RawSo3, Sis3, Sos3]
  ): DisposeFunction = {
    cycleExecution(main, driver1, driver2, driver3).run()
  }

  @inline def apply[
    Si1 <: Sink, RawSi1 <: RawSink, So1 <: Source, RawSo1 <: RawSource, Sis1 <: Sinks, Sos1 <: Sources,
    Si2 <: Sink, RawSi2 <: RawSink, So2 <: Source, RawSo2 <: RawSource, Sis2 <: Sinks, Sos2 <: Sources,
    Si3 <: Sink, RawSi3 <: RawSink, So3 <: Source, RawSo3 <: RawSource, Sis3 <: Sinks, Sos3 <: Sources,
    Si4 <: Sink, RawSi4 <: RawSink, So4 <: Source, RawSo4 <: RawSource, Sis4 <: Sinks, Sos4 <: Sources
  ](
    main: (Sos1 with Sos2 with Sos3 with Sos4) => (Sis1 with Sis2 with Sis3 with Sis4),
    driver1: Driver[Si1, RawSi1, So1, RawSo1, Sis1, Sos1],
    driver2: Driver[Si2, RawSi2, So2, RawSo2, Sis2, Sos2],
    driver3: Driver[Si3, RawSi3, So3, RawSo3, Sis3, Sos3],
    driver4: Driver[Si4, RawSi4, So4, RawSo4, Sis4, Sos4]
  ): DisposeFunction = {
    cycleExecution(main, driver1, driver2, driver3, driver4).run()
  }

  @inline
  private def cycleExecution[Sos <: Sources, Sis <: Sinks](
    main: Sos => Sis,
    drivers: Driver[_, _, _, _, _, _]*
  ): Execution[Sos, Sis] = {
    RawCycle(main, combineDrivers(drivers), cycleOptions)
  }

  private def combineDrivers(drivers: Seq[Driver[_, _, _, _, _, _]]): RawDrivers = {
    // @TODO[Integrity] A lot of unsafe asInstanceOf magic in this method
    val rawDriverPairs: Seq[(String, DriverFunction[Sink, Source])] = drivers
      .map(_.asInstanceOf[Driver[Sink, RawSink, Source, RawSource, Sinks, Sources]])
      .map(driver => (driver.key, js.Any.fromFunction3(driver.driverFunction)))

    js.Dictionary[DriverFunction[Sink, Source]](rawDriverPairs: _*).asInstanceOf[RawDrivers]
  }
}
