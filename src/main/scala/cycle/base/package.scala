package cycle

import cycle.xstream.adapter.XStreamAdapter

import scala.scalajs.js

package object base {

  type BaseDriverFunction[Si, AnySo] = js.Function3[Si, XStreamAdapter, String, AnySo]
  type RawDriverFunction[Si <: RawSink, RawSo <: RawSource] = BaseDriverFunction[Si, RawSo]
  type DriverFunction[Si <: Sink, So <: Source] = BaseDriverFunction[Si, So]

  /** OriginStream is a stream from whatever stream library is used by a particular Cycle driver */
  type OriginStream = js.Object

  type DisposeFunction = js.Function0[Unit]

  type OptionalDisposeFunction = js.UndefOr[DisposeFunction]

  type StreamSubscribeFunction[TStream, TCycleObserver] = js.Function2[TStream, TCycleObserver, OptionalDisposeFunction]
}
