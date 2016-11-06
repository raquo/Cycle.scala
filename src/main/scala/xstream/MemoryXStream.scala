package xstream

import scala.scalajs.js

@js.native
class MemoryXStream[T](producer: js.UndefOr[InternalProducer[T]]) extends XStream[T](producer) {
  override def map[U](project: js.Function1[T, U]): MemoryXStream[U] = js.native
  override def mapTo[U](projectedValue: U): MemoryXStream[U] = js.native
  override def take(amount: Int): MemoryXStream[T] = js.native
  override def endWhen(other: XStream[_]): MemoryXStream[T] = js.native
  override def replaceError[E](replace: js.Function1[E, XStream[T]]): MemoryXStream[T] = js.native // @TODO should `E` type exist here? In Typescript it's `any`
  override def debug(spy: js.Function1[T, Unit]): MemoryXStream[T] = js.native // @TODO is it ok to change argument names? In TS this is labelOrSpy
  override def debug(label: String): MemoryXStream[T] = js.native // @TODO is it ok to change argument names? In TS this is labelOrSpy
  override def debug(): MemoryXStream[T] = js.native
}

