package xstream

import cycle.base.{Observer, OptionalDisposeFunction, OriginStream, StreamSubscribeFunction}
import cycle.xstream.adapter.ListenerCycleObserver

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}

@ScalaJSDefined
/** Note: names `_start` and `_stop` are used internally by cycle.js, do not create members with such names */
class Producer[T] private (__start: Listener[T] => Unit, __stop: () => Unit) extends RawProducer[T] {

  override def start(rawListener: RawListener[T]): Unit = __start(Listener.fromRawListener(rawListener))

  override def stop(): Unit = __stop()
}

object Producer {

  def create[T](start: Listener[T] => Unit, stop: () => Unit): Producer[T] = {
    new Producer[T](start, stop)
  }

  def fromOriginStream[T](
    originStream: OriginStream,
    originStreamSubscribe: StreamSubscribeFunction[OriginStream, Observer[T]]
  ): Producer[T] = {
    var dispose: OptionalDisposeFunction = js.undefined
    Producer.create[T](
      start = listener => {
        dispose = originStreamSubscribe(originStream, new ListenerCycleObserver[T](listener))
      },
      stop = () => dispose.toOption.foreach(_())
    )
  }
}
