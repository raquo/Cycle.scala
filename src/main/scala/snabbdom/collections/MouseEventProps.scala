package snabbdom.collections

import snabbdom.Util.{eventProp, MouseEventCallback}

/**
  * Mouse Events: triggered by a mouse, or similar user actions.
  */
trait MouseEventProps {

  /**
    * The click event is raised when the user clicks on an element. The click
    * event will occur after the mousedown and mouseup events.
    *
    * MDN
    */
  lazy val onClick = eventProp[MouseEventCallback]("click")

  /**
    * The dblclick event is fired when a pointing device button (usually a
    * mouse button) is clicked twice on a single element.
    *
    * MDN
    */
  lazy val onDblClick = eventProp[MouseEventCallback]("dblclick")

  /**
    * Script to be run when an element is dragged
    */
  val onDrag = eventProp[MouseEventCallback]("drag")

  /**
    * Script to be run at the end of a drag operation
    */
  lazy val onDragEnd = eventProp[MouseEventCallback]("dragend")

  /**
    * Script to be run when an element has been dragged to a valid drop target
    */
  lazy val onDragEnter = eventProp[MouseEventCallback]("dragenter")

  /**
    * Script to be run when an element leaves a valid drop target
    */
  lazy val onDragLeave = eventProp[MouseEventCallback]("dragleave")

  /**
    * Script to be run when an element is being dragged over a valid drop target
    */
  lazy val onDragOver = eventProp[MouseEventCallback]("dragover")

  /**
    * Script to be run at the start of a drag operation
    */
  lazy val onDragStart = eventProp[MouseEventCallback]("dragstart")

  /**
    * Script to be run when dragged element is being dropped
    */
  lazy val onDrop = eventProp[MouseEventCallback]("drop")

  /**
    * The mousedown event is raised when the user presses the mouse button.
    *
    * MDN
    */
  lazy val onMouseDown = eventProp[MouseEventCallback]("mousedown")

  /**
    * The mousemove event is raised when the user moves the mouse.
    *
    * MDN
    */
  lazy val onMouseMove = eventProp[MouseEventCallback]("mousemove")

  /**
    * The mouseout event is raised when the mouse leaves an element (e.g, when
    * the mouse moves off of an image in the web page, the mouseout event is
    * raised for that image element).
    *
    * MDN
    */
  lazy val onMouseOut = eventProp[MouseEventCallback]("mouseout")

  /**
    * The mouseover event is raised when the user moves the mouse over a
    * particular element.
    *
    * MDN
    */
  lazy val onMouseOver = eventProp[MouseEventCallback]("mouseover")

  /**
    * The mouseup event is raised when the user releases the mouse button.
    *
    * MDN
    */
  lazy val onMouseUp = eventProp[MouseEventCallback]("mouseup")

  /**
    * Specifies the function to be called when the window is scrolled.
    *
    * MDN
    */
  lazy val onScroll = eventProp[MouseEventCallback]("scroll")

  /**
    * Fires when the mouse wheel rolls up or down over an element
    */
  lazy val onWheel = eventProp[MouseEventCallback]("wheel")
}
