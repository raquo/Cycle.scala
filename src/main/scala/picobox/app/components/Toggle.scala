package picobox.app.components

import cycle.dom.DOMSource
import snabbdom.Snabbdom.tags._
import snabbdom.Snabbdom.attrs._
import snabbdom.VNode
import xstream.XStream
import org.scalajs.dom.raw.{HTMLInputElement, MouseEvent}

class Toggle(
  private val DOMSource: DOMSource
) {
  private val click$ = DOMSource.select("#entry #toggle").events[MouseEvent]("click")

  val DOM$: XStream[VNode] = click$
    .map((ev: MouseEvent) => ev.target.asInstanceOf[HTMLInputElement].checked)
    .startWith(false)
    .map((toggled: Boolean) => {
      div(
        input(id := "toggle", typ := "checkbox"),
        label(`for` := "toggle", "TOGGLE MEEEE"),
        p(if (toggled) "ONN" else "off")
      )
    })
}
