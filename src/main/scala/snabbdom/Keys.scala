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

class Style (val jsKey: String, val cssKey: String) extends Key[Any, StylePair[Any]] {
  override def := (value: Any): StylePair[Any] = new StylePair[Any](this, value.toString)
  def := (value: Int): StylePair[Int] = new StylePair[Int](this, value)
  def := (value: Double): StylePair[Double] = new StylePair[Double](this, value)
  def := (value: String): StylePair[String] = new StylePair[String](this, value)
}




