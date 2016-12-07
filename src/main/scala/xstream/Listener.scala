package xstream

import cycle.base.Observer

import scala.scalajs.js.annotation.ScalaJSDefined

// @TODO[performance] replace Options with js.UndefOr?

@ScalaJSDefined
class Listener[T] private(
  maybeNext: Option[T => Unit] = None,
  maybeError: Option[_ => Unit] = None,
  maybeComplete: Option[() => Unit] = None
) extends RawListener[T] {

  override def next(x: T): Unit = maybeNext.getOrElse(Listener.noop1[T])(x)

  override def error[E](e: E): Unit = maybeError.getOrElse(Listener.noop1[E])

  override def complete(): Unit = maybeComplete.getOrElse(Listener.noop0)
}

object Listener {

  private def noop0 = () => {}

  private def noop1[T] = (x: T) => {}

  def create[T](next: T => Unit): Listener[T] =
    new Listener(maybeNext = Some(next))

  def create[T, E](next: T => Unit, error: E => Unit): Listener[T] =
    new Listener(maybeNext = Some(next), maybeError = Some(error))

  def create[T, E](next: T => Unit, complete: () => Unit): Listener[T] =
    new Listener(maybeNext = Some(next), maybeComplete = Some(complete))

  def create[T, E](next: T => Unit, error: E => Unit, complete: () => Unit): Listener[T] =
    new Listener(maybeNext = Some(next), maybeError = Some(error), maybeComplete = Some(complete))

  def fromCycleObserver[T](observer: Observer[T]): Listener[T] = new Listener(
    maybeNext = Some(observer.next _),
    maybeError = Some(observer.error _),
    maybeComplete = Some(observer.complete _)
  )

  def fromRawListener[T](rawListener: RawListener[T]): Listener[T] = new Listener(
    maybeNext = Some(rawListener.next _),
    maybeError = Some(rawListener.error _),
    maybeComplete = Some(rawListener.complete _)
  )
}
