package snabbdom.collections

import snabbdom.Util.{GenericEventCallback, eventProp}

/**
  * Miscellaneous Events
  */
trait MiscellaneousEventProps extends SharedEventProps {

  /**
    * Fires when a <menu> element is shown as a context menu
    */
  lazy val onShow = eventProp[GenericEventCallback]("show")
  /**
    * Fires when the user opens or closes the <details> element
    */
  lazy val onToggle = eventProp[GenericEventCallback]("toggle")
}
