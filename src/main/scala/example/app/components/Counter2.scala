package example.app.components

import com.raquo.xstream.XStream
import com.raquo.xstream.XStream.merge
import com.raquo.cycle.dom.{DOMSink, DOMSinks, DOMSources}
import com.raquo.cycle.isolate.Isolate
import com.raquo.snabbdom.VNode
import com.raquo.snabbdom.tags._
import com.raquo.snabbdom.attrs._
import com.raquo.snabbdom.events._

import scala.scalajs.js.Dynamic.{global => g}
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Counter2 private (
  val DOM: DOMSink[Nothing]
) extends DOMSinks[Nothing]

object Counter2 {

  def apply(): (DOMSources => Counter2) = Isolate { sources =>
    val $increment = sources.DOM.select("#entry #inc").$event(onClick).mapTo(1)
    val $decrement = sources.DOM.select("#entry #dec").$event(onClick).mapTo(-1)

    val $count: XStream[Int] = merge($increment, $decrement)
      .startWith(0)
      .fold((a: Int, b: Int) => a + b, seed = 0)

    val $vnode: XStream[VNode] = $count.map { count =>
      div(
        button(id := "inc", typ := "button", "+"),
        button(id := "dec", typ := "button", "–"),
        p(s"Count: $count")
      )
    }

    new Counter2($vnode)
  }
}
