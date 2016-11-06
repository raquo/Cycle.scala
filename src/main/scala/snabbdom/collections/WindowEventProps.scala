package snabbdom.collections

import snabbdom.Util.{GenericEventCallback, prop}

/**
  * Window Events
  *
  */
trait WindowEventProps extends SharedEventProps {

  /**
    * The load event fires at the end of the document loading process. At this
    * point, all of the objects in the document are in the DOM, and all the
    * images and sub-frames have finished loading.
    *
    * MDN
    */
  lazy val onLoad = prop[GenericEventCallback]("onload")

  /**
    * Script to be run after the document is printed
    */
  lazy val onAfterPrint = prop[GenericEventCallback]("onafterprint")

  /**
    * Script to be run before the document is printed
    */
  lazy val onBeforePrint = prop[GenericEventCallback]("onbeforeprint")

  /**
    * Script to be run when the document is about to be unloaded
    */
  lazy val onBeforeUnload = prop[GenericEventCallback]("onbeforeunload")

  /**
    * Script to be run when there has been changes to the anchor part of the a URL
    */
  lazy val onHashChange = prop[GenericEventCallback]("onhashchange")

  /**
    * Script to be run when the message is triggered
    */
  lazy val onMessage = prop[GenericEventCallback]("onmessage")

  /**
    * Script to be run when the browser starts to work offline
    */
  lazy val onOffline = prop[GenericEventCallback]("onoffline")

  /**
    * Script to be run when the browser starts to work online
    */
  lazy val onOnline = prop[GenericEventCallback]("ononline")

  /**
    * Script to be run when a user navigates away from a page
    */
  lazy val onPageHide = prop[GenericEventCallback]("onpagehide")

  /**
    * Script to be run when a user navigates to a page
    */
  lazy val onPageShow = prop[GenericEventCallback]("onpageshow")

  /**
    * Script to be run when the window's history changes
    */
  lazy val onPopState = prop[GenericEventCallback]("onpopstate")

  /**
    * Fires when the browser window is resized
    */
  lazy val onResize = prop[GenericEventCallback]("onresize")

  /**
    * Script to be run when a Web Storage area is updated
    */
  lazy val onStorage = prop[GenericEventCallback]("onstorage")

  /**
    * Fires once a page has unloaded (or the browser window has been closed)
    */
  lazy val onUnload = prop[GenericEventCallback]("onunload")
}
