package picobox.app.components

import xstream.{MemoryXStream, XStream}
import cycle.dom.DOMSource
import snabbdom.VNode

import scala.scalajs.js.|

import org.scalajs.dom.raw.MouseEvent

import snabbdom.Snabbdom.tags._
import snabbdom.Snabbdom.attrs._

import scala.scalajs.js.Dynamic.{global => g}

// @TODO pass DOMSource implicitly?

class Counter2(
  private val DOMSource: DOMSource
) {
  val count$: MemoryXStream[Int] = {

    val increment$ = DOMSource.select("#entry #inc").events[MouseEvent]("click").map((ev: MouseEvent) => 1)
    val decrement$ = DOMSource.select("#entry #dec").events[MouseEvent]("click").map((ev: MouseEvent) => -1)

    XStream.merge(increment$, decrement$)
      .startWith(0)
      .fold((a: Int, b: Int) => a + b, seed = 0)
  }

  val DOM$: XStream[VNode] = {
    count$
      .map((count: Int) => {
        div(
          button(id := "inc", typ := "button", "+"),
          button(id := "dec", typ := "button", "–"),
          p(s"Count: $count")
        )
      })
  }

//  implicit def xxx(value: Int | Int): Int = {
//    value.asInstanceOf[Int]
//  }
//
//  implicit def xxx[A](value: A | A): A = {
//    value.asInstanceOf[A]
//  }
//
//  implicit def xxx[A](value: XStream[A | A]): XStream[A] = {
//    value.asInstanceOf[XStream[A]]
//  }
//
//  implicit def xxx[A](value: MemoryXStream[A | A]): MemoryXStream[A] = {
//    value.asInstanceOf[MemoryXStream[A]]
//  }
}
