package snabbdom.collections

import snabbdom.EventProp
import snabbdom.Util.{GenericEventCallback, eventProp}

/**
  * Clipboard Events
  */
trait ClipboardEventProps {

  /**
    * Fires when the user copies the content of an element
    */
  lazy val onCopy: EventProp[GenericEventCallback] = eventProp("copy")

  /**
    * Fires when the user cuts the content of an element
    */
  lazy val onCut: EventProp[GenericEventCallback] = eventProp("cut")

  /**
    * Fires when the user pastes some content in an element
    */
  lazy val onPaste: EventProp[GenericEventCallback] = eventProp("paste")
}
