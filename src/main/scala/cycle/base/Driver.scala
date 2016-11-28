package cycle.base

import cycle.xstream.adapter.XStreamAdapter

trait Driver[-Si <: Sink, -RawSi <: RawSink, +So <: Source, +RawSo <: RawSource, -Sis <: Sinks, +Sos <: Sources] {

  // @TODO make private
  val rawDriver: RawDriverFunction[RawSi, RawSo]

  val key: String

  def driverFunction(sink: Si, streamAdapter: XStreamAdapter, key: String): So
}
