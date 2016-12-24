package picobox.app

import picobox.app.components.MyApp
import org.scalajs.dom.raw.Event
import org.scalajs.dom.document
import cycle.dom.CycleDOM.makeDOMDriver
import cycle.http.CycleHTTP.makeHTTPDriver
import cycle.xstream.run.XStreamRun

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}

object Init extends js.JSApp {

  def main(): Unit = {
    document.addEventListener("DOMContentLoaded", (e: Event) => {
      g.console.log("=== DOMContentLoaded ===")

      XStreamRun(
        MyApp.apply,
        makeDOMDriver("#entry", transposition = true),
        makeHTTPDriver()
      )
    })
  }
}
