package com.raquo.snabbdom.collections

import com.raquo.snabbdom.EventProp
import com.raquo.snabbdom.Util.{GenericEventCallback, eventProp}

/**
  * Miscellaneous Events
  */
trait MiscellaneousEventProps extends SharedEventProps {

  /**
    * Fires when a <menu> element is shown as a context menu
    */
  lazy val onShow: EventProp[GenericEventCallback] = eventProp("show")
  /**
    * Fires when the user opens or closes the <details> element
    */
  lazy val onToggle: EventProp[GenericEventCallback] = eventProp("toggle")
}
