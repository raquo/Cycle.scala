package picobox.app.components

import xstream.XStream
import cycle.dom.DOMSource
import snabbdom.{Modifier, VNode}
import snabbdom.Snabbdom.tags._
import snabbdom.Snabbdom.attrs._
import org.scalajs.dom.MouseEvent

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}
import scala.util.Random

// @TODO pass DOMSource implicitly?

class Counter(
  private val DOMSource: DOMSource
) {
  private val incClick$ = DOMSource.select("#inc").events[MouseEvent]("click")
  private val decClick$ = DOMSource.select("#dec").events[MouseEvent]("click")

//  private val incClick$ = XStream.create[MouseEvent]()
//  private val decClick$ = XStream.create[MouseEvent]()

  val count$: XStream[Int] = {
    val increment$: XStream[Int] = incClick$.map((ev: MouseEvent) => 1)
    val decrement$: XStream[Int] = decClick$.map((ev: MouseEvent) => -1)

    XStream.merge(increment$, decrement$)
      .startWith(0)
      .fold((acc: Int, nextVal: Int) => acc + nextVal, 0)
  }

  val DOM$: XStream[VNode] = {
//    val x = (e: MouseEvent) => {
//      println("call")
//      js.debugger()
//    }
    count$
      .map((count: Int) => {
        div(
          "Foo",
          "bar",
          button(id := "inc" /*onClick := incClick$*/, typ := "button", "+"),
          button(id := "dec", /*onClick := x,*/ typ := "button", "–"),
          p(s"Count = $count, rand = ${Random.nextInt()}"),
          "Hello",
          "world",
          button(id := "xxx", typ := "button", "+"),
          a(href := "#yolo", "boooo"),
          Seq[Modifier]("yo", b("lo"), "OMG"),
          Some(i("some")),
          Option("maybe"),
          None,
          a(href := "#yolo", "hoo")
        )
      })
      .debug()
  }
}
