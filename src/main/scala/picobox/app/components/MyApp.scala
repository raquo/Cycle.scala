package picobox.app.components

import cycle.dom.{DOMSink, DOMSinks, DOMSources}
import cycle.http.{HTTPSink, HTTPSinks, HTTPSources, RequestOptions}
import snabbdom.VNode
import snabbdom.tags.{br, div}
import xstream.XStream

import scala.scalajs.js.Dynamic.{global => g}
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class MyApp(
  val DOM: DOMSink,
  val HTTP: HTTPSink
) extends DOMSinks with HTTPSinks

object MyApp {
  def apply(sources: DOMSources with HTTPSources): MyApp = {
    g.console.log("App Sources:", sources)
    val counter1 = Counter(sources)
    val counter2 = Counter(sources)

    val vnode$ = XStream
      .combine(counter1.DOM, counter2.DOM)
      .map((c1: VNode, c2: VNode) => div(c1, br(), br(), br(), c2))

    val httpRequest$: HTTPSink = XStream.create[RequestOptions]()

    new MyApp(vnode$, httpRequest$)
  }
}
