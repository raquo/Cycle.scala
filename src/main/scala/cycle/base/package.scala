package cycle

import scala.scalajs.js

package object base {

  /** OriginStream is a stream from whatever stream library is used by a particular Cycle driver */
  type OriginStream = js.Object

  type DisposeFunction = js.Function0[Unit]

  type OptionalDisposeFunction = js.UndefOr[DisposeFunction]

  type StreamSubscribeFunction[TStream, TCycleObserver] = js.Function2[TStream, TCycleObserver, OptionalDisposeFunction]
}
