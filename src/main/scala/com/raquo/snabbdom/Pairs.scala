package com.raquo.snabbdom

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

/**
  * A [[Pair]] is a modifier that can be applied to a [VNode] to e.g. set an attribute to a particular value
  */
@ScalaJSDefined
trait Pair[K <: Key[V, Self], V, Self <: Pair[K, V, Self]] extends Modifier

@ScalaJSDefined
class AttrPair[V] (
  val attr: Attr[V],
  val value: V
) extends Pair[Attr[V], V, AttrPair[V]] {

  @inline def applyTo(tag: VNode): Unit = tag.addAttr[V](this)
}

@ScalaJSDefined
class EventPropPair[V <: js.Function] (
  val eventProp: EventProp[V],
  val value: V
) extends Pair[EventProp[V], V, EventPropPair[V]] {

  @inline def applyTo(tag: VNode): Unit = tag.addEventProp[V](this)
}

@ScalaJSDefined
class PropPair[V] (
  val prop: Prop[V],
  val value: V
) extends Pair[Prop[V], V, PropPair[V]] {

  @inline def applyTo(tag: VNode): Unit = tag.addProp[V](this)
}

@ScalaJSDefined
class StylePair[V] (
  val style: Style[V],
  val value: V
) extends Pair[Style[V], V, StylePair[V]] {

  @inline def applyTo(tag: VNode): Unit = tag.addStyle[V](this)
}
