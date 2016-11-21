package cycle.xstream.adapter

import cycle.base.Observer
import xstream.{Listener, XStream}

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class ListenerCycleObserver[T](listener: Listener[T]) extends Observer[T] {

  def next(x: T): Unit = {
//    g.console.log("ListenerCycleObserver.next")
    listener.next(x)
  }

  def error[E](e: E): Unit = {
//    g.console.log("ListenerCycleObserver.error")
    listener.error(e)
  }

  def complete(c: T): Unit = {
//    g.console.log("ListenerCycleObserver.complete[c]")
    // @TODO[Integrity] Is it ok that we're ignoring c here?
    listener.complete()
  }

  def complete(): Unit = {
//    g.console.log("ListenerCycleObserver.complete[c]")
    listener.complete()
  }
}
