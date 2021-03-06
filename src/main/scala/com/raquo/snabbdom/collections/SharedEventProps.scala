package com.raquo.snabbdom.collections

import com.raquo.snabbdom.EventProp
import com.raquo.snabbdom.Util.{EventCallback, eventProp}
import org.scalajs.dom.ErrorEvent

trait SharedEventProps {

  /**
    * Script to be run when an error occurs when the file is being loaded
    */
  lazy val onerror: EventProp[EventCallback[ErrorEvent]] = eventProp("onerror")
}
