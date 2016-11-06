package snabbdom.collections

import snabbdom.Util.{GenericEventCallback, eventProp}

trait FormEventProps {

  /**
    * The blur event is raised when an element loses focus.
    *
    * MDN
    */
  lazy val onBlur = eventProp[GenericEventCallback]("blur")

  /**
    * The change event is fired for input, select, and textarea elements
    * when a change to the element's value is committed by the user.
    *
    * MDN
    */
  lazy val onChange = eventProp[GenericEventCallback]("change")

  /**
    * The focus event is raised when the user sets focus on the given element.
    *
    * MDN
    */
  lazy val onFocus = eventProp[GenericEventCallback]("focus")

  /**
    * The select event only fires when text inside a text input or textarea is
    * selected. The event is fired after the text has been selected.
    *
    * MDN
    */
  lazy val onSelect = eventProp[GenericEventCallback]("select")

  /**
    * The submit event is raised when the user clicks a submit button in a form
    * (<input type="submit"/>).
    *
    * MDN
    */
  lazy val onSubmit = eventProp[GenericEventCallback]("submit")

  /**
    * The reset event is fired when a form is reset.
    *
    * MDN
    */
  lazy val onReset = eventProp[GenericEventCallback]("reset")

  /**
    * Script to be run when a context menu is triggered
    */
  lazy val onContextMenu = eventProp[GenericEventCallback]("contextmenu")

  /**
    * Script to be run when an element gets user input
    */
  lazy val onInput = eventProp[GenericEventCallback]("input")

  /**
    * Script to be run when an element is invalid
    */
  lazy val onInvalid = eventProp[GenericEventCallback]("invalid")

  /**
    * Fires when the user writes something in a search field (for <input="search">)
    */
  lazy val onSearch = eventProp[GenericEventCallback]("search")
}
