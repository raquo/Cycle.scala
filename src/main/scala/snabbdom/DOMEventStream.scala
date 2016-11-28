package snabbdom

import org.scalajs.dom.raw.Event
import xstream.{RawStream, XStream}

// @TODO[API] make this part of DOMSource package!? Or not?
class DOMEventStream[E <: Event]
  extends XStream[E](rawStream = RawStream.create())
