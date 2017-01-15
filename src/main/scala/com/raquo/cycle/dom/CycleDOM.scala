package com.raquo.cycle.dom

import com.raquo.snabbdom
import com.raquo.snabbdom.{AttrsModule, EventsModule, PropsModule, StyleModule}
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}
import scala.scalajs.js.|

@ScalaJSDefined
class RawDOMDriverOptions(
  val modules: js.Array[snabbdom.Module],
  val transposition: Boolean = false
) extends js.Object

@js.native
@JSImport("@cycle/dom", JSImport.Namespace)
object RawCycleDOM extends js.Object {
  def makeDOMDriver(
    container: String | HTMLElement,
    options: js.UndefOr[RawDOMDriverOptions] = js.undefined
  ): RawDOMDriver = js.native
}

object CycleDOM {

  private val defaultModules = js.Array(AttrsModule, EventsModule, PropsModule, StyleModule)

  // @TODO add ability to use custom modules somehow

  /** @param containerSelector CSS selector of an existing HTML element where application will be mounted */
  def makeDOMDriver(containerSelector: String, transposition: Boolean): DOMDriver = {
    val options = new RawDOMDriverOptions(
      modules = defaultModules,
      transposition = transposition
    )
    new DOMDriver(RawCycleDOM.makeDOMDriver(containerSelector, options))
  }

  /** @param containerElement existing HTML element where application will be mounted */
  def makeDOMDriver(containerElement: HTMLElement, transposition: Boolean): DOMDriver = {
    val options = new RawDOMDriverOptions(
      modules = defaultModules,
      transposition = transposition
    )
    new DOMDriver(RawCycleDOM.makeDOMDriver(containerElement, options))
  }
}
