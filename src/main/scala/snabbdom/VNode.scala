package snabbdom

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}

@js.native
trait VNodeData extends js.Any {
  var attrs: js.UndefOr[js.Dynamic] = js.native
  var on: js.UndefOr[js.Dynamic] = js.native
  var props: js.UndefOr[js.Dynamic] = js.native
  var style: js.UndefOr[js.Dynamic] = js.native
}

// @TODO[Elegance][Performance] Should VTextNode be related to VNode?

@ScalaJSDefined
sealed trait ChildVNode extends js.Any

@ScalaJSDefined
class TextVNode(val text: String) extends Modifier with ChildVNode {
  def applyTo(vnode: VNode): Unit = vnode.addTextChild(this)
}

@ScalaJSDefined
class IterableVNode(val modifiers: Iterable[Modifier]) extends Modifier {
  def applyTo(vnode: VNode): Unit = modifiers.foreach(_.applyTo(vnode))
}

@ScalaJSDefined
class VNode(tagName: String) extends Modifier with ChildVNode {

  type Children = js.Array[ChildVNode]

  var sel: String = tagName

  var key: js.UndefOr[String] = js.undefined

  var data: VNodeData = js.Dynamic.literal().asInstanceOf[VNodeData]

  var text: js.UndefOr[String] = js.undefined

  var children: js.UndefOr[Children] = js.undefined

  /**
    * Apply the given modifiers (e.g. additional children, or new attributes) to the [[VNode]].
    */
  @JSName("apply")
  def apply(modifiers: Modifier*): VNode = {
    // ugly while loop for speed
    var i = 0
    while (i < modifiers.length) {
      modifiers(i).applyTo(this)
      i += 1
    }
    this
  }

  def applyTo(vnode: VNode): Unit = {
    vnode.addChild(this)
  }

  def addAttr[TValue](attrPair: AttrPair[TValue]): Unit = {
    val attrKey = attrPair.attr.key
    val attrValue: js.Any = if (attrPair.value.isInstanceOf[Boolean]) {
      attrPair.value.asInstanceOf[js.Any]
    } else {
      attrPair.value.toString.asInstanceOf[js.Any]
    }
    if (data.attrs.isEmpty) {
      data.attrs = js.Dynamic.literal(attrKey -> attrValue)
    } else {
      data.attrs.asInstanceOf[js.Dynamic].updateDynamic(attrKey)(attrValue)
    }
  }

  def addEventProp[TValue <: js.Function](eventPropPair: EventPropPair[TValue]): Unit = {
    val eventKey = eventPropPair.eventProp.key
    val eventValue = eventPropPair.value
    if (data.on.isEmpty) {
      data.on = js.Dynamic.literal(eventKey -> eventValue)
    } else {
      data.on.asInstanceOf[js.Dynamic].updateDynamic(eventKey)(eventValue)
    }
  }

  def addProp[TValue](propPair: PropPair[TValue]): Unit = {
    val propKey = propPair.prop.key
    val propValue: js.Any = propPair.value.asInstanceOf[js.Any]
    if (data.props.isEmpty) {
      data.props = js.Dynamic.literal(propKey -> propValue)
    } else {
      data.props.asInstanceOf[js.Dynamic].updateDynamic(propKey)(propValue)
    }
  }

  def addStyle[TValue](stylePair: StylePair[TValue]): Unit = {
    val styleKey = stylePair.style.jsKey
    val styleValue: js.Any = stylePair.value.asInstanceOf[js.Any]
    if (data.style.isEmpty) {
      data.style = js.Dynamic.literal(styleKey -> styleValue)
    } else {
      data.style.asInstanceOf[js.Dynamic].updateDynamic(styleKey)(styleValue)
    }
  }

  def addChild(vnode: VNode): Unit = {
    if (text.isDefined) {
      addChildToList(new TextVNode(text.asInstanceOf[String]))
      text = js.undefined
    }
    addChildToList(vnode)
  }

  def addTextChild(textNode: TextVNode): Unit = {
    val hasChildren = children.isDefined && children.asInstanceOf[Children].length > 0
    if (hasChildren) {
      addChildToList(textNode)
    } else if (text.isEmpty) {
      text = textNode.text
    } else {
      text += textNode.text
    }
  }

  @inline
  private def addChildToList(child: ChildVNode): Unit = {
    if (children.isEmpty) {
      children = new Children()
    }
    children.asInstanceOf[Children].push(child)
  }
}



