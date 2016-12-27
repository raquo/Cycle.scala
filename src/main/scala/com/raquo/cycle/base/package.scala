package com.raquo.cycle

import com.raquo.xstream.{Listener, MemoryStream, XStream}

import scala.scalajs.js

package object base {

  type RawDriver[Si, So] = js.Function3[Si, XStreamAdapter, String, So]

  type RawDrivers = js.Dictionary[RawDriver[_, _]]

  /** OriginStream is a stream from whatever stream library is used by a particular Cycle driver */
  type OriginStream = js.Object

  type DisposeFunction = js.Function0[Unit]

  type OptionalDisposeFunction = js.UndefOr[DisposeFunction]

  type StreamSubscribeFunction[Stream, CycleObserver] = js.Function2[Stream, CycleObserver, OptionalDisposeFunction]

  type XStreamAdapter = StreamAdapter[XStream, MemoryStream]

  implicit class RichObserver[T, E <: js.Error] (val observer: Observer[T, E]) extends AnyVal {

    def toListener: Listener[T, E] = new Listener[T, E] {
      override val next: js.Function1[T, Unit] = observer.next _
      override val error: js.Function1[E, Unit] = observer.error _
      override val complete: js.Function0[Unit] = js.Any.fromFunction0(observer.complete)
    }
  }
}
