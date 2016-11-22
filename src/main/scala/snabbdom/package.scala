import org.scalajs.dom.raw.Event
import snabbdom.Util.EventCallback
import snabbdom.collections._

import scala.scalajs.js.Dynamic.{global => g}
import xstream.XStream

package object snabbdom {

  object tags extends Tags

  object allTags extends Tags with Tags2

  object attrs extends Attrs with InputAttrs with GlobalAttrs

  object events extends MouseEventProps with KeyboardEventProps with ClipboardEventProps

  object styles extends Styles

  implicit def stringToVNode(text: String): TextVNode = new TextVNode(text)

  implicit def iterableToModifier[T <: Modifier](modifiers: Iterable[T]): IterableVNode = new IterableVNode(modifiers)

  implicit def noneToModifier(none: None.type): NoModifier.type = NoModifier

  implicit def optionToModifier[T <: Modifier](maybeModifier: Option[T]): Modifier = {
    maybeModifier match {
      case Some(modifier) => modifier
      case None => NoModifier
    }
  }

  implicit def stringOptionToModifier(maybeText: Option[String]): Modifier = {
    maybeText match {
      case Some(text) => text
      case None => NoModifier
    }
  }

  implicit def streamToStreamNode(stream: XStream[VNode]): StreamVNode = {
    new StreamVNode(stream)
  }

  /** Note: You need to import this def explicitly */
  implicit def eventStreamToCallback[TEvent <: Event](stream: XStream[TEvent]): EventCallback[TEvent] = {
    def addEventToStream(ev: TEvent): Unit = {
//      g.console.log("addEventToStream")
//      g.console.log(ev)
      stream.shamefullySendNext(ev)
    }
//    g.console.log("streamToCallback")
    addEventToStream _
  }
}
