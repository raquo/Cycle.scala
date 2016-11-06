package picobox.app

import cycle.base.{Drivers_DOM, Sinks_DOM, Sources_DOM}
import picobox.app.components.Counter

import scala.scalajs.js.annotation.JSExportAll
import cycle.xstreamrun.XStreamRun
import org.scalajs.dom.raw.Event
import org.scalajs.dom.document
import cycle.dom.CycleDOM.makeDOMDriver

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}


class AppComponent {
  def apply(sources: Sources_DOM): Sinks_DOM = {
    g.console.log("main")
    g.console.log("Sources:", sources)
    val child = new Counter(sources.DOM)
//    val child = new Toggle(sources.DOM)
    val sinks = new Sinks_DOM(DOM = child.DOM$)
    g.console.log("Sinks:", sinks)
    sinks
  }
}

@JSExportAll
object App extends js.JSApp {
  def main(): Unit = {
    document.addEventListener("DOMContentLoaded", (e: Event) => {
      println("Hello world!")

      g.console.log("+")
      g.console.log(((new AppComponent).apply _).isInstanceOf[Function1[Sources_DOM, Sinks_DOM]])

      val DOMDriver = makeDOMDriver("#entry")
      val drivers = new Drivers_DOM(DOMDriver)
      //      js.debugger()
      val component = new AppComponent
      XStreamRun.run(component.apply _, drivers)
    })
  }
}
