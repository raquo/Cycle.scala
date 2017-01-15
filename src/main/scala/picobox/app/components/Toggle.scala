package picobox.app.components

import com.raquo.cycle.dom.DOMSource
import com.raquo.snabbdom.tags._
import com.raquo.snabbdom.attrs._
import com.raquo.snabbdom.events._
import com.raquo.snabbdom.VNode
import com.raquo.xstream.XStream
import org.scalajs.dom.raw.{HTMLInputElement, MouseEvent}

class Toggle(
  private val DOMSource: DOMSource
) {
  private val click$ = DOMSource.select("#entry #toggle").$event(onClick)

  val DOM$: XStream[VNode, Nothing] = click$
    .map(ev => ev.target.asInstanceOf[HTMLInputElement].checked)
    .startWith(false)
    .map(toggled => {
      div(
        input(id := "toggle", typ := "checkbox"),
        label(`for` := "toggle", "TOGGLE MEEEE"),
        p(if (toggled) "ONN" else "off")
      )
    })
}
