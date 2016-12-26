package xstream

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

// @TODO[performance] replace Options with js.UndefOr?

// @TODO[API] change this to object method somehow? Should this be a value class?

// @TODO[API] this pattern is weird. There must be a better way. See examples in Scala.js 0.14 release notes.

@ScalaJSDefined
trait Listener[-T] extends js.Object {

  val next: js.Function1[T, Unit]

  val error: js.Function1[Any, Unit] // Any should be E

  val complete: js.Function0[Unit]
}

object Listener {

  // @TODO[Elegance] Is there a better way to define empty no-op functions?

  private[xstream] def noop0(): Unit = ()

  private[xstream] def noop1[T](x: T): Unit = ()

  def apply[T](
    maybeNext: Option[T => Unit],
    maybeError: Option[Any => Unit],
    maybeComplete: Option[() => Unit]
  ): Listener[T] = new Listener[T] {
    override val next: js.Function1[T, Unit] = js.Any.fromFunction1(maybeNext.getOrElse(noop1[T]))
    override val error: js.Function1[Any, Unit] = js.Any.fromFunction1(maybeError.getOrElse(noop1[Any]))
    override val complete: js.Function0[Unit] = js.Any.fromFunction0(maybeComplete.getOrElse(noop0))
  }

  def create[T](onNext: T => Unit): Listener[T] = new Listener[T] {
    override val next: js.Function1[T, Unit] = onNext
    override val error: js.Function1[Any, Unit] = noop1 _
    override val complete: js.Function0[Unit] = noop0 _
  }

  // TODO: This should have error type
  def create[T](onNext: T => Unit, onError: Any => Unit): Listener[T] = new Listener[T] {
    override val next: js.Function1[T, Unit] = onNext
    override val error: js.Function1[Any, Unit] = onError
    override val complete: js.Function0[Unit] = noop0 _
  }

  def create[T](onNext: T => Unit, onComplete: () => Unit): Listener[T] = new Listener[T] {
    override val next: js.Function1[T, Unit] = onNext
    override val error: js.Function1[Any, Unit] = noop1 _
    override val complete: js.Function0[Unit] = onComplete
  }

  def create[T](onNext: T => Unit, onError: Any => Unit, onComplete: () => Unit): Listener[T] = new Listener[T] {
    override val next: js.Function1[T, Unit] = onNext
    override val error: js.Function1[Any, Unit] = onError
    override val complete: js.Function0[Unit] = onComplete
  }
}
