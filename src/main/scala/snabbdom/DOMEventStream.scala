package snabbdom

import org.scalajs.dom.raw.Event
import xstream.{RawStream, XStream}

class DOMEventStream[E <: Event]
  extends XStream[E](rawStream = RawStream.create())
