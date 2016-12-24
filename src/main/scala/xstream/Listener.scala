package xstream

import cycle.base.Observer

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

// @TODO[performance] replace Options with js.UndefOr?

// @TODO[API] change this to object method somehow? Should this be a value class?

// @TODO[API] this pattern is weird. There must be a better way. See examples in Scala.js 0.14 release notes.

@ScalaJSDefined
trait Listener[T] extends js.Object {

  def next(x: T): Unit

  def error[E](e: E): Unit

  def complete(): Unit
}

object Listener {

  private[xstream] def noop0 = () => {}

  private[xstream] def noop1[T] = (x: T) => {}

  def create[T](next: T => Unit): Listener[T] =
    new RichListener(maybeNext = Some(next))

  def create[T, E](next: T => Unit, error: E => Unit): Listener[T] =
    new RichListener(maybeNext = Some(next), maybeError = Some(error))

  def create[T, E](next: T => Unit, complete: () => Unit): Listener[T] =
    new RichListener(maybeNext = Some(next), maybeComplete = Some(complete))

  def create[T, E](next: T => Unit, error: E => Unit, complete: () => Unit): Listener[T] =
    new RichListener(maybeNext = Some(next), maybeError = Some(error), maybeComplete = Some(complete))

  def fromCycleObserver[T](observer: Observer[T]): Listener[T] = new RichListener(
    maybeNext = Some(observer.next _),
    maybeError = Some(observer.error _),
    maybeComplete = Some(observer.complete _)
  )

  def fromRawListener[T](rawListener: Listener[T]): Listener[T] = new RichListener(
    maybeNext = Some(rawListener.next _),
    maybeError = Some(rawListener.error _),
    maybeComplete = Some(rawListener.complete _)
  )
}

@ScalaJSDefined
class RichListener[T] private[xstream] (
  maybeNext: Option[T => Unit] = None,
  maybeError: Option[_ => Unit] = None,
  maybeComplete: Option[() => Unit] = None
) extends Listener[T] {

  override def next(x: T): Unit = maybeNext.getOrElse(Listener.noop1[T])(x)

  override def error[E](e: E): Unit = maybeError.getOrElse(Listener.noop1[E])

  override def complete(): Unit = maybeComplete.getOrElse(Listener.noop0)
}
