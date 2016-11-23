package picobox.app.components

import xstream.XStream
import cycle.dom.DOMSource
import snabbdom.VNode

import org.scalajs.dom.raw.MouseEvent

import snabbdom.tags._
import snabbdom.attrs._

import scala.scalajs.js.Dynamic.{global => g}

// @TODO pass DOMSource implicitly?

class Counter2(
  private val DOMSource: DOMSource
) {
  val count$: XStream[Int] = {

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
}
