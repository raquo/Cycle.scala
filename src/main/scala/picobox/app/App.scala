package picobox.app

import cycle.base.{Drivers_DOM, Sources_DOM}
import picobox.app.components.Counter
import cycle.xstreamrun.{Main_DOM, RunConfig_DOM, XStreamRun}
import org.scalajs.dom.raw.Event
import org.scalajs.dom.document
import cycle.dom.CycleDOM.makeDOMDriver
import snabbdom.VNode
import snabbdom.tags._
import xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.Dynamic.{global => g}

@ScalaJSDefined
class App(sources: Sources_DOM) extends Main_DOM(sources) {
  g.console.log("main")
  g.console.log("Sources:", sources)
  val child1 = new Counter(sources.DOM)
  val child2 = new Counter(sources.DOM)

  def render(DOM1: VNode, DOM2: VNode): VNode = div(DOM1, br(), br(), DOM2)

  val DOM = XStream
    .combine(child1.DOM$, child2.DOM$)
    .map[VNode]((render _).tupled)
}

object App extends js.JSApp {
  def main(): Unit = {
    document.addEventListener("DOMContentLoaded", (e: Event) => {
      g.console.log("+")

      val appDrivers = new Drivers_DOM(makeDOMDriver("#entry"))
      val appConfig = new RunConfig_DOM(new App(_), appDrivers)
      XStreamRun.run(appConfig)
    })
  }
}
