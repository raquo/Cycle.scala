package snabbdom

import scala.scalajs.js

/**
  * A [[Key]] Represents the left hand side of [[Pair]]s, e.g. attribute names
  */
trait Key[TValue, TPair] {
  def := (value: TValue): TPair
}

class Attr[TValue] (val key: String) extends Key[TValue, AttrPair[TValue]] {
  override def := (value: TValue): AttrPair[TValue] = new AttrPair[TValue](this, value)
}

class EventProp[TValue <: js.Function] (val key: String) extends Key[TValue, EventPropPair[TValue]] {
  override def := (value: TValue): EventPropPair[TValue] = new EventPropPair[TValue](this, value)
}

class Prop[TValue] (val key: String) extends Key[TValue, PropPair[TValue]] {
  override def := (value: TValue): PropPair[TValue] = new PropPair[TValue](this, value)
}

class Style[TValue] (val jsKey: String, val cssKey: String) extends Key[TValue, StylePair[TValue]] {
  override def := (value: TValue): StylePair[TValue] = new StylePair[TValue](this, value)
}
