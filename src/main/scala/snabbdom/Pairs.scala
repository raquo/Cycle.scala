package snabbdom

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * A [[Pair]] is a modifier that can be applied to a [VNode] to e.g. set an attribute to a particular value
  */
@ScalaJSDefined
trait Pair[TKey, TValue] extends Modifier

@ScalaJSDefined
class AttrPair[TValue] (
  val attr: Attr[TValue],
  val value: TValue
) extends Pair[Attr[TValue], TValue] {
  def applyTo(tag: VNode): Unit = tag.addAttr[TValue](this)
}

@ScalaJSDefined
class EventPropPair[TValue <: js.Function] (
  val eventProp: EventProp[TValue],
  val value: TValue
) extends Pair[EventProp[TValue], TValue] {
  def applyTo(tag: VNode): Unit = tag.addEventProp[TValue](this)
}

@ScalaJSDefined
class PropPair[TValue] (
  val prop: Prop[TValue],
  val value: TValue
) extends Pair[Prop[TValue], TValue] {
  def applyTo(tag: VNode): Unit = tag.addProp[TValue](this)
}

@ScalaJSDefined
class StylePair[TValue] (
  val style: Style[TValue],
  val value: TValue
) extends Pair[Style[TValue], TValue] {
  def applyTo(tag: VNode): Unit = tag.addStyle[TValue](this)
}
