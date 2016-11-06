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
  def makeDOMDriver: js.Function2[String, js.UndefOr[DOMDriverOptions], DOMSource] = js.native
}

object CycleDOM {
  val defaultModules = js.Array[Module](AttrsModule, EventsModule, PropsModule, StyleModule)

  /** @param selector CSS selector of an existing HTML element where application will be mounted */
  def makeDOMDriver(selector: String, options: DOMDriverOptions = new DOMDriverOptions()): DOMSource = {
    RawCycleDOM.makeDOMDriver(selector, options)
  }
}
