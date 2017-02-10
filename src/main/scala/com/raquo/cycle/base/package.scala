package com.raquo.cycle

import com.raquo.xstream.{Listener, MemoryStream, EStream}

import scala.scalajs.js
import scala.scalajs.js.|

package object base {

  type RawDriver[Si, So] = js.Function3[Si, XStreamAdapter, String, So]

  type RawDrivers = js.Dictionary[RawDriver[_, _]]

  /** OriginStream is a stream from whatever stream library is used by a particular Cycle driver */
  type OriginStream = js.Object

  type DisposeFunction = js.Function0[Unit]

  type OptionalDisposeFunction = js.UndefOr[DisposeFunction]

  type StreamSubscribeFunction[EStream, CycleObserver] = js.Function2[EStream, CycleObserver, OptionalDisposeFunction]

  type XStreamAdapter = StreamAdapter[EStream, MemoryStream]

  implicit class RichObserver[T, EE <: Exception] (val observer: Observer[T, EE]) extends AnyVal {

    def toListener: Listener[T, EE] = Listener[T, EE](
      onNext = observer.next,
      onExpectedError = (err: EE) => observer.error(err),
      onUnexpectedError = (err: Exception | js.Error) => observer.error(err),
      onComplete = observer.complete
    )
  }
}
