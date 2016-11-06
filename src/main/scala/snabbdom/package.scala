import org.scalajs.dom.raw.Event
import snabbdom.Util.EventCallback
import xstream.XStream

import scala.scalajs.js

package object snabbdom {

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

  implicit def streamToCallback[TEvent <: Event](stream: XStream[TEvent]): EventCallback[TEvent] = {
    def addEventToStream(ev: TEvent): Unit = {
      println("addEventToStream")
      println(ev)
      js.debugger()
      stream.shamefullySendNext(ev)
    }
    println("streamToCallback")
    addEventToStream _
  }
}
