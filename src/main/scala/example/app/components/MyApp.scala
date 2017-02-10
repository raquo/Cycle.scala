package example.app.components

import com.raquo.cycle.dom.{DOMSink, DOMSinks, DOMSources}
import com.raquo.cycle.http.{HTTPSink, HTTPSinks, HTTPSources}
import com.raquo.snabbdom.VNode
import com.raquo.snabbdom.tags.{br, div}
import com.raquo.xstream.XStream.combine

import scala.scalajs.js.Dynamic.{global => g}
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class MyApp(
  val DOM: DOMSink[Nothing],
  val HTTP: HTTPSink[Nothing]
) extends DOMSinks[Nothing] with HTTPSinks[Nothing]

object MyApp {
  def apply(sources: DOMSources with HTTPSources): MyApp = {
    g.console.log("App Sources:", sources)
    val counter1 = Counter()(sources)
//    val counter2 = Counter(intervalFactor = 2)(sources)
    val counter3 = Counter2()(sources)
    val randomUser = RandomUser()(sources)

    val vnode$ = combine(counter1.DOM, counter3.DOM, randomUser.DOM)
      .map((c1: VNode, c2: VNode, c3: VNode) => div(c1, br(), br(), br(), c2, br(), br(), c3))
//      .combine(counter1.DOM, counter2.DOM, kitten.DOM)
//      .map((c1: VNode, c2: VNode, k: VNode) => div(c1, br(), br(), br(), c2, br(), br(), k))

    new MyApp(vnode$, randomUser.HTTP)
  }
}
