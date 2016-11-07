package picobox.app

import cycle.base.{Drivers_DOM, Sources_DOM}
import picobox.app.components.Counter
import cycle.xstreamrun.{Main_DOM, RunConfig_DOM, XStreamRun}
import org.scalajs.dom.raw.Event
import org.scalajs.dom.document
import cycle.dom.CycleDOM.makeDOMDriver

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.Dynamic.{global => g}

@ScalaJSDefined
class App(sources: Sources_DOM) extends Main_DOM(sources) {
  g.console.log("main")
  g.console.log("Sources:", sources)
  val child = new Counter(sources.DOM)
//  val child = new Toggle(sources.DOM)

  val DOM = child.DOM$
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
