package com.raquo.cycle.dom

import com.raquo.snabbdom.{AttrsModule, EventsModule, Module, PropsModule, StyleModule}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

@ScalaJSDefined
class RawDOMDriverOptions(
  val modules: js.Array[Module],
  val transposition: Boolean = false
) extends js.Object

@js.native
@JSImport("@cycle/dom", JSImport.Namespace)
object RawCycleDOM extends js.Object {
  def makeDOMDriver(selector: String): RawDOMDriver = js.native
  def makeDOMDriver(selector: String, options: RawDOMDriverOptions): RawDOMDriver = js.native
}

object CycleDOM {
  private val defaultModules = js.Array[Module](AttrsModule, EventsModule, PropsModule, StyleModule)

  // @TODO add ability to use custom modules somehow

  /** @param selector CSS selector of an existing HTML element where application will be mounted */
  def makeDOMDriver(selector: String, transposition: Boolean = false): DOMDriver = {
    val options = new RawDOMDriverOptions(
      modules = defaultModules,
      transposition = transposition
    )
    new DOMDriver(RawCycleDOM.makeDOMDriver(selector, options))
  }
}
