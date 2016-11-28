package picobox.app.components

import xstream.XStream
import cycle.dom.{DOMSink, DOMSinks, DOMSources}
import cycle.isolate.Isolator
import snabbdom.VNode
import org.scalajs.dom.raw.MouseEvent
import snabbdom.tags._
import snabbdom.attrs._

import scala.scalajs.js.Dynamic.{global => g}
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Counter2 private (
  val DOM: DOMSink
) extends DOMSinks

object Counter2 {

  private def main(sources: DOMSources): Counter2 = {
    val $increment = sources.DOM.select("#entry #inc").events[MouseEvent]("click").map((ev: MouseEvent) => 1)
    val $decrement = sources.DOM.select("#entry #dec").events[MouseEvent]("click").map((ev: MouseEvent) => -1)

    val $count: XStream[Int] = XStream.merge($increment, $decrement)
      .startWith(0)
      .fold((a: Int, b: Int) => a + b, seed = 0)

    val $vnode: XStream[VNode] = $count
      .map((count: Int) => {
        div(
          button(id := "inc", typ := "button", "+"),
          button(id := "dec", typ := "button", "–"),
          p(s"Count: $count")
        )
      })

    new Counter2($vnode)
  }

  def apply(sources: DOMSources): Counter2 = Isolator.isolate(main)(sources)
}
