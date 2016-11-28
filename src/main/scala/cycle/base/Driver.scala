package cycle.base

import cycle.xstream.adapter.XStreamAdapter

trait Driver[-Si <: Sink, +So <: Source, -Sis <: Sinks, +Sos <: Sources] {

  /** [[key]] must match the field name of [[Si]] in [[Sis]], and [[So]] in [[Sos]] */
  val key: String

  /** [[driverFunction]] will be passed to Cycle.js. The key provided here is the same as [[key]]. */
  def driverFunction(sink: Si, streamAdapter: XStreamAdapter, key: String): So
}
