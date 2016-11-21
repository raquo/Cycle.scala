package picobox.app

import cycle.base.{Drivers_DOM, Sinks_DOM, Sources_DOM}
import picobox.app.components.Counter
import org.scalajs.dom.raw.Event
import org.scalajs.dom.document
import cycle.dom.CycleDOM.makeDOMDriver
import cycle.xstream.run.{RunConfig_DOM, XStreamRun}
import snabbdom.VNode
import snabbdom.tags._
import xstream.XStream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.Dynamic.{global => g}

@ScalaJSDefined
class App(sources: Sources_DOM) extends Sinks_DOM {
  g.console.log("App Sources:", sources)
  val counter1 = new Counter(sources.DOM)
  val counter2 = new Counter(sources.DOM)

  val DOM = XStream
    .combine(counter1.DOM$, counter2.DOM$)
    .map((c1: VNode, c2: VNode) => div(c1, br(), br(), br(), c2))
}

object App extends js.JSApp {
  def main(): Unit = {
    document.addEventListener("DOMContentLoaded", (e: Event) => {
      g.console.log("=== DOMContentLoaded ===")
      val appDrivers = new Drivers_DOM(makeDOMDriver("#entry"))
      val appConfig = new RunConfig_DOM(new App(_), appDrivers)
      XStreamRun(appConfig)
    })
  }
}
