package cycle

import scala.scalajs.js
//import monix.reactive.{Observable => MonixObservable}
//import monix.reactive.subjects.{PublishSubject => MonixPublishSubject}

package object base {
  type DisposeFunction = js.Function0[Unit]
//  type OptionalDisposeFunction = js.UndefOr[DisposeFunction]
//  type StreamSubscribeFunction[TObservable, TCycleObserver] = js.Function2[TObservable, TCycleObserver, OptionalDisposeFunction]
}
