package cycle.dom

import snabbdom.{AttrsModule, EventsModule, Module, PropsModule, StyleModule}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

@ScalaJSDefined
class DOMDriverOptions(
  val modules: js.Array[Module] = CycleDOM.defaultModules,
  val transposition: Boolean = false
) extends js.Object

@js.native
@JSImport("@cycle/dom", JSImport.Namespace)
object RawCycleDOM extends js.Object {
  def makeDOMDriver(selector: String, options: js.UndefOr[DOMDriverOptions]): RawDOMSource = js.native
}

object CycleDOM {
  val defaultModules = js.Array[Module](AttrsModule, EventsModule, PropsModule, StyleModule)

  /** @param selector CSS selector of an existing HTML element where application will be mounted */
  def makeDOMDriver(selector: String, transposition: Boolean = false): DOMSource = {
    new DOMSource(RawCycleDOM.makeDOMDriver(selector, new DOMDriverOptions(transposition = transposition)))
  }
}
