package cycle.base

import cycle.dom.RawDOMSource

import scala.scalajs.js

@js.native
trait Sources extends js.Object

@js.native
class Sources_DOM() extends Sources {
  val DOM: RawDOMSource = js.native
}
