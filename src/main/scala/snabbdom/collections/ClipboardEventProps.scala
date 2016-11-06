package snabbdom.collections

import snabbdom.Util.{GenericEventCallback, eventProp}

/**
  * Clipboard Events
  */
trait ClipboardEventProps {

  /**
    * Fires when the user copies the content of an element
    */
  lazy val onCopy = eventProp[GenericEventCallback]("copy")

  /**
    * Fires when the user cuts the content of an element
    */
  lazy val onCut = eventProp[GenericEventCallback]("cut")

  /**
    * Fires when the user pastes some content in an element
    */
  lazy val onPaste = eventProp[GenericEventCallback]("paste")
}
