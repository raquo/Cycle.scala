package snabbdom.collections

import snabbdom.Util.{EventCallback, eventProp}
import org.scalajs.dom.ErrorEvent

trait SharedEventProps {

  /**
    * Script to be run when an error occurs when the file is being loaded
    */
  lazy val onerror = eventProp[EventCallback[ErrorEvent]]("onerror")
}
