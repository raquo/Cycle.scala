package cycle.base

import cycle.dom.DOMSource

import scala.scalajs.js

@js.native
trait Sources extends js.Object

@js.native
class Sources_DOM() extends Sources {
  val DOM: DOMSource = js.native
}
