import org.scalajs.dom.raw.Event
import snabbdom.Util.EventCallback
import snabbdom.VNode.{Child, Children}
import snabbdom.collections._
import xstream.XStream
import xstream.OptionalImplicits.ShamefulStream

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}

package object snabbdom {

  object tags extends Tags

  object allTags extends Tags with Tags2

  object attrs extends Attrs with InputAttrs with GlobalAttrs

  object events extends MouseEventProps with KeyboardEventProps with ClipboardEventProps

  object styles extends Styles

  // @TODO[API][Performance] Implicitception – does this harm compile or runtime performance?
  implicit def optionToModifier[T](maybeModifier: Option[T])(implicit toModifier: T => Modifier): Modifier = {
    maybeModifier match {
      case Some(modifier) => modifier
      case None => NoModifier
    }
  }

  implicit class StreamEventProp[Ev <: Event] (val eventProp: EventProp[EventCallback[Ev]]) extends AnyVal {

    def sendTo(stream: XStream[Ev]): EventPropPair[EventCallback[Ev]] = {
      @inline def addEventToStream(event: Ev): Unit = stream.shamefullySendNext(event)

      new EventPropPair[EventCallback[Ev]](eventProp, addEventToStream _)
    }

    @inline def -->(stream: XStream[Ev]): EventPropPair[EventCallback[Ev]] =
      sendTo(stream)
  }

  // @TODO[Elegance][Performance] Should VTextNode be related to VNode?

  @ScalaJSDefined
  implicit class TextNode(val text: String) extends Modifier {

    @inline def applyTo(vnode: VNode): Unit = vnode.addTextChild(this)
  }

  @ScalaJSDefined
  implicit class SeqNode(val modifiers: Seq[Modifier]) extends Modifier {

    @inline def applyTo(vnode: VNode): Unit = vnode.apply(modifiers: _*)
  }

  @ScalaJSDefined
  implicit class StreamNode(val stream: XStream[VNode]) extends Modifier {

    @inline def applyTo(vnode: VNode): Unit = vnode.addStreamChild(this)
  }

  implicit class RichNode(val thisNode: VNode) extends AnyVal {

    /**
      * Apply the given modifiers (e.g. additional children, or new attributes) to the [[VNode]].
      */
    @JSName("applyModifiers")
    def apply(modifiers: Modifier*): VNode = {
      // ugly while loop for speed
      var i = 0
      while (i < modifiers.length) {
        val modifier = modifiers(i)
        val hasMethod = modifier.asInstanceOf[js.Dynamic].applyTo.asInstanceOf[js.UndefOr[js.Function]] != js.undefined
        if (hasMethod) {
          modifiers(i).applyTo(thisNode)
        } else {
          // @TODO[Integrity] - This modifier is a vnode created outside of Cycle.scala
          // @TODO We do want to support such nodes, but we need to clean this up, maybe add some more types
          thisNode.addChild(modifier.asInstanceOf[VNode])
        }

        i += 1
      }
      thisNode
    }

    private[snabbdom] def addAttr[TValue](attrPair: AttrPair[TValue]): Unit = {
      val attrKey = attrPair.attr.key
      val attrValue: js.Any = if (attrPair.value.isInstanceOf[Boolean]) {
        attrPair.value.asInstanceOf[js.Any]
      } else {
        attrPair.value.toString.asInstanceOf[js.Any]
      }
      if (thisNode.data.attrs.isEmpty) {
        thisNode.data.attrs = js.Dynamic.literal(attrKey -> attrValue)
      } else {
        thisNode.data.attrs.asInstanceOf[js.Dynamic].updateDynamic(attrKey)(attrValue)
      }
    }

    private[snabbdom] def addEventProp[TValue <: js.Function](eventPropPair: EventPropPair[TValue]): Unit = {
      val eventKey = eventPropPair.eventProp.key
      val eventValue = eventPropPair.value
      if (thisNode.data.on.isEmpty) {
        thisNode.data.on = js.Dynamic.literal(eventKey -> eventValue)
      } else {
        thisNode.data.on.asInstanceOf[js.Dynamic].updateDynamic(eventKey)(eventValue)
      }
    }

    private[snabbdom] def addProp[TValue](propPair: PropPair[TValue]): Unit = {
      val propKey = propPair.prop.key
      val propValue: js.Any = propPair.value.asInstanceOf[js.Any]
      if (thisNode.data.props.isEmpty) {
        thisNode.data.props = js.Dynamic.literal(propKey -> propValue)
      } else {
        thisNode.data.props.asInstanceOf[js.Dynamic].updateDynamic(propKey)(propValue)
      }
    }

    private[snabbdom] def addStyle[TValue](stylePair: StylePair[TValue]): Unit = {
      val styleKey = stylePair.style.jsKey
      val styleValue: js.Any = stylePair.value.asInstanceOf[js.Any]
      if (thisNode.data.style.isEmpty) {
        thisNode.data.style = js.Dynamic.literal(styleKey -> styleValue)
      } else {
        thisNode.data.style.asInstanceOf[js.Dynamic].updateDynamic(styleKey)(styleValue)
      }
    }

    private[snabbdom] def addChild(vnode: VNode): Unit = {
      if (thisNode.text.isDefined) {
        addChildToList(new TextNode(thisNode.text.asInstanceOf[String]))
        thisNode.text = js.undefined
      }
      addChildToList(vnode)
    }

    private[snabbdom] def addTextChild(textNode: TextNode): Unit = {
      val hasChildren = thisNode.children.isDefined && thisNode.children.asInstanceOf[Children].length > 0
      if (hasChildren) {
        addChildToList(textNode)
      } else if (thisNode.text.isEmpty) {
        thisNode.text = textNode.text
      } else {
        thisNode.text += textNode.text
      }
    }

    @inline private[snabbdom] def addStreamChild(streamNode: StreamNode): Unit = {
      addChildToList(streamNode.stream)
    }

    @inline private def addChildToList(child: Child): Unit = {
      if (thisNode.children.isEmpty) {
        thisNode.children = new Children()
      }
      thisNode.children.asInstanceOf[Children].push(child)
    }
  }
}
