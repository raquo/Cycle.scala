package cycle.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("@cycle/dom", JSImport.Namespace)
object CycleDOM extends js.Object {
//  type TAttrs = js.Dictionary[js.Dictionary[Any]]
//  type Children = js.Array[Any]

  def makeDOMDriver: js.Function2[String, js.UndefOr[DOMDriverOptions], DOMSource] = js.native

  // @TODO remove these typings
//  def h(selector: String): js.Object = js.native
//
//  def h(selector: String, children: Any): js.Object = js.native
//
//  def h(selector: String, children: Children): js.Object = js.native
//
//  def h(selector: String, attrs: TAttrs): js.Object = js.native
//
//  def h(selector: String, attrs: TAttrs, children: Any): js.Object = js.native
//
//  def h(selector: String, attrs: TAttrs, children: Children): js.Object = js.native
}

