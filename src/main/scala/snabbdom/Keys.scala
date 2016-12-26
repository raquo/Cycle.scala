package snabbdom

import scala.scalajs.js

/**
  * A [[Key]] Represents the left hand side of [[Pair]]s, e.g. attribute names
  */
trait Key[V, P <: Pair[_, V, P]] {
  /** Create a key + value pair */
  def := (value: V): P
}

class Attr[V] (val key: String) extends Key[V, AttrPair[V]] {
  override def := (value: V): AttrPair[V] =
    new AttrPair[V](this, value)
}

class EventProp[V <: js.Function] (val key: String) extends Key[V, EventPropPair[V]] {
  override def := (value: V): EventPropPair[V] =
    new EventPropPair[V](this, value)
}

class Prop[V] (val key: String) extends Key[V, PropPair[V]] {
  override def := (value: V): PropPair[V] =
    new PropPair[V](this, value)
}

class Style[V] (val jsKey: String, val cssKey: String) extends Key[V, StylePair[V]] {
  override def := (value: V): StylePair[V] =
    new StylePair[V](this, value)
}
