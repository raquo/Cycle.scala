package cycle.xstream.adapter

import cycle.base.Observer
import xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class XStreamCycleObserver[T](stream: XStream[T]) extends Observer[T] {

  def next(x: T): Unit = {
//    g.console.log("XStreamCycleObserver.next")
    stream.shamefullySendNext(x)
  }

  def error[E](e: E): Unit = {
//    g.console.log("XStreamCycleObserver.error")
    stream.shamefullySendError(e)
  }

  def complete(c: T): Unit = {
//    g.console.log("XStreamCycleObserver.complete[c]")
    // @TODO[Integrity] Is it ok that we're ignoring c here?
    stream.shamefullySendComplete()
  }

  def complete(): Unit = {
//    g.console.log("XStreamCycleObserver.complete")
    stream.shamefullySendComplete()
  }
}
