package xstream

import scalajs.js
import scalajs.js.|

import scala.scalajs.js.JSConverters

// @TODO Add documentation here

@js.native
class XStream[T](producer: js.UndefOr[InternalProducer[T]]) extends js.Object {
  def addListener(listener: PartialListener[T]): Unit = js.native
  def removeListener(listener: Listener[T]): Unit = js.native
  def subscribe(listener: Listener[T]): Subscription[T] = js.native
  def map[U](project: js.Function1[T, U]): XStream[U] = js.native
  def mapTo[U](projectedValue: U): XStream[U] = js.native
  //  def filter[S <: T](passes: js.Function1[T, Boolean]): Stream[S] = js.native // @TODO will this even work in Scala? Probably change name. In typescript a type predicate is used
  def filter(passes: js.Function1[T, Boolean]): XStream[T] = js.native
  def take(amount: Int): XStream[T] = js.native
  def drop(amount: Int): XStream[T] = js.native
  def last(): XStream[T] = js.native
  def startWith(initial: T): MemoryXStream[T] = js.native
  def endWhen(other: XStream[_]): XStream[T] = js.native
  def fold[R](accumulate: js.Function2[R, T, R], seed: R): MemoryXStream[R] = js.native
  def replaceError[E](replace: js.Function1[E, XStream[T]]): XStream[T] = js.native // @TODO should `E` type exist here? In Typescript it's `any`
  def flatten[R]: XStream[R] = js.native // @TODO. This only makes sense if T is Stream[R]. How do enforce this limitation in Scala?
  def compose[U](operator: js.Function1[XStream[T], XStream[U]]): XStream[U] = js.native
  def remember(): MemoryXStream[T] = js.native
  def debug(spy: js.Function1[T, Unit]): XStream[T] = js.native // @TODO is it ok to change argument names? In TS this is labelOrSpy
  def debug(label: String): XStream[T] = js.native // @TODO is it ok to change argument names? In TS this is labelOrSpy
  def debug(): XStream[T] = js.native
  def imitate(target: XStream[T]): Unit = js.native
  def shamefullySendNext(value: T): Unit = js.native
  def shamefullySendError[E](error: E): Unit = js.native
  def shamefullySendComplete(): Unit = js.native
  def setDebugListener(listener: Listener[T]): Unit = js.native
}

object XStream {
  type FromInput[T] = js.Promise[T] | XStream[T] | js.Array[T] // | Observable[T] @TODO is this some sort of TS observable?

  //  implicit def tuple2ToJsArray[T1, T2](tuple: (T1, T2)): js.Array[T1 | T2] = js.Array(tuple._1, tuple._2)

  def jsArrayToTuple2[T1, T2](arr: js.Array[T1 | T2]): (T1, T2) = {
    (arr(0).asInstanceOf[T1], arr(1).asInstanceOf[T2])
  }
  def jsArrayToTuple3[T1, T2, T3](arr: js.Array[T1 | T2 | T3]): (T1, T2, T3) = {
    (arr(0).asInstanceOf[T1], arr(1).asInstanceOf[T2], arr(2).asInstanceOf[T3])
  }
  def jsArrayToTuple4[T1, T2, T3, T4](arr: js.Array[T1 | T2 | T3 | T4]): (T1, T2, T3, T4) = {
    (arr(0).asInstanceOf[T1], arr(1).asInstanceOf[T2], arr(2).asInstanceOf[T3], arr(3).asInstanceOf[T4])
  }
  def jsArrayToTuple5[T1, T2, T3, T4, T5](arr: js.Array[T1 | T2 | T3 | T4 | T5]): (T1, T2, T3, T4, T5) = {
    (arr(0).asInstanceOf[T1], arr(1).asInstanceOf[T2], arr(2).asInstanceOf[T3], arr(3).asInstanceOf[T4], arr(4).asInstanceOf[T5])
  }

  @inline
  def create[T](producer: Option[Producer[T]] = None): XStream[T] = RawXStream.create[T](JSConverters.JSRichOption(producer).orUndefined) // @TODO How to make conversion automatic?

  @inline
  def createWithMemory[T](producer: Option[Producer[T]] = None): MemoryXStream[T] = RawXStream.createWithMemory[T](JSConverters.JSRichOption(producer).orUndefined) // @TODO How to make conversion automatic?

  @inline
  def never(): XStream[Nothing] = RawXStream.never()

  @inline
  def empty(): XStream[Nothing] = RawXStream.empty()

  @inline
  def throwError[T](error: T): XStream[T] = RawXStream.`throw`(error)

  @inline
  def from[T](input: FromInput[T]): XStream[T] = RawXStream.from(input)

  @inline
  def of[T](items: T*): XStream[T] = RawXStream.fromArray(JSConverters.JSRichGenTraversableOnce(items).toJSArray)

  @inline
  def fromSeq[T](seq: Seq[T]): XStream[T] = RawXStream.fromArray(JSConverters.JSRichGenTraversableOnce(seq).toJSArray)

  @inline
  def fromPromise[T](promise: js.Promise[T]): XStream[T] = RawXStream.fromPromise(promise)

  //  def fromObservable[T](observable: Any): Stream[T] = js.native // @TODO What observable? Why??

  @inline
  def periodic(period: Int): XStream[Int] = RawXStream.periodic(period)

  @inline
  def merge[T](streams: XStream[T]*): XStream[T] = RawXStream.merge(streams: _*)

//  @inline
//  def merge[T](s1: XStream[T], s2: XStream[T]): XStream[T] = RawXStream.merge(s1, s2)
////  def merge[T1, T2](s1: XStream[T1], s2: XStream[T2]): XStream[T1 | T2] = RawXStream.merge(s1, s2)
//
//  @inline
//  def merge[T1, T2, T3](s1: XStream[T1], s2: XStream[T2], s3: XStream[T3]): XStream[T1 | T2 | T3] = RawXStream.merge(s1, s2, s3)
//
//  @inline
//  def merge[T1, T2, T3, T4](s1: XStream[T1], s2: XStream[T2], s3: XStream[T3], s4: XStream[T4]): XStream[T1 | T2 | T3 | T4] = RawXStream.merge(s1, s2, s3, s4)
//
//  @inline
//  def merge[T1, T2, T3, T4, T5](s1: XStream[T1], s2: XStream[T2], s3: XStream[T3], s4: XStream[T4], s5: XStream[T5]): XStream[T1 | T2 | T3 | T4 | T5] = RawXStream.merge(s1, s2, s3, s4, s5)

  @inline
  def combine[T1, T2](s1: XStream[T1], s2: XStream[T2]): XStream[(T1, T2)] = {
    RawXStream.combine(s1, s2).map((arr: js.Array[T1 | T2]) => jsArrayToTuple2[T1, T2](arr))
  }
  @inline
  def combine[T1, T2, T3](s1: XStream[T1], s2: XStream[T2], s3: XStream[T3]): XStream[(T1, T2, T3)] = {
    RawXStream.combine(s1, s2, s3).map((arr: js.Array[T1 | T2 | T3]) => jsArrayToTuple3[T1, T2, T3](arr))
  }
  @inline
  def combine[T1, T2, T3, T4](s1: XStream[T1], s2: XStream[T2], s3: XStream[T3], s4: XStream[T4]): XStream[(T1, T2, T3, T4)] = {
    RawXStream.combine(s1, s2, s3, s4).map((arr: js.Array[T1 | T2 | T3 | T4]) => jsArrayToTuple4[T1, T2, T3, T4](arr))
  }
  @inline
  def combine[T1, T2, T3, T4, T5](s1: XStream[T1], s2: XStream[T2], s3: XStream[T3], s4: XStream[T4], s5: XStream[T5]): XStream[(T1, T2, T3, T4, T5)] = {
    RawXStream.combine(s1, s2, s3, s4, s5).map((arr: js.Array[T1 | T2 | T3 | T4 | T5]) => jsArrayToTuple5[T1, T2, T3, T4, T5](arr))
  }
}






//class XStream[T](jsXStream: JsXStream[T]) {
//  def addListener(listener: PartialListener[T]): Unit = jsXStream.addListener(listener)
//  def removeListener(listener: Listener[T]): Unit = jsXStream.removeListener(listener)
//  def subscribe(listener: Listener[T]): Subscription[T] = jsXStream.subscribe(listener)
//  def map[U](project: T => U): XStream[U] = new XStream(jsXStream.map(project))
//  def mapTo[U](projectedValue: U): XStream[U] = new XStream(jsXStream.mapTo(projectedValue))
//  //  def filter[S <: T](passes: js.Function1[T, Boolean]): Stream[S] = js.native // @TODO will this even work in Scala? Probably change name. In typescript a type predicate is used
//  def filter(passes: T => Boolean): XStream[T] = new XStream(jsXStream.filter(passes))
//  def take(amount: Int): XStream[T] = new XStream(jsXStream.take(amount))
//  def drop(amount: Int): XStream[T] = new XStream(jsXStream.drop(amount))
//  def last(): XStream[T] = new XStream(jsXStream.last())
//  def startWith(initial: T): MemoryXStream[T] = new MemoryXStream(jsXStream.startWith(initial))
//  def endWhen(other: JsXStream[_]): XStream[T] = new XStream(jsXStream.endWhen(other)) // @TOTO JS type
//  def fold[R](accumulate: js.Function2[R, T, R], seed: R): MemoryXStream[R] = js.native
//  def replaceError[E](replace: js.Function1[E, JsXStream[T]]): JsXStream[T] = js.native // @TODO should `E` type exist here? In Typescript it's `any`
//  def flatten[R]: JsXStream[R] = js.native // @TODO. This only makes sense if T is Stream[R]. How do enforce this limitation in Scala?
//  def compose[U](operator: js.Function1[JsXStream[T], JsXStream[U]]): JsXStream[U] = js.native
//  def remember(): MemoryXStream[T] = js.native
//  def debug(spy: js.Function1[T, Unit]): JsXStream[T] = js.native // @TODO is it ok to change argument names? In TS this is labelOrSpy
//  def debug(label: String): JsXStream[T] = js.native // @TODO is it ok to change argument names? In TS this is labelOrSpy
//  def debug(): JsXStream[T] = js.native
//  def imitate(target: JsXStream[T]): Unit = js.native
//  def shamefullySendNext(value: T): Unit = js.native
//  def shamefullySendError[E](error: E): Unit = js.native
//  def shamefullySendComplete(): Unit = js.native
//  def setDebugListener(listener: Listener[T]): Unit = js.native
//}
//
//class MemoryXStream[T](jsXStream: JsXStream) extends XStream[T] {
//  override def map[U](project: js.Function1[T, U]): JsMemoryXStream[U] = js.native
//  override def mapTo[U](projectedValue: U): JsMemoryXStream[U] = js.native
//  override def take(amount: Int): JsMemoryXStream[T] = js.native
//  override def endWhen(other: JsXStream[_]): JsMemoryXStream[T] = js.native
//  override def replaceError[E](replace: js.Function1[E, JsXStream[T]]): JsMemoryXStream[T] = js.native // @TODO should `E` type exist here? In Typescript it's `any`
//  override def debug(spy: js.Function1[T, Unit]): JsMemoryXStream[T] = js.native // @TODO is it ok to change argument names? In TS this is labelOrSpy
//  override def debug(label: String): JsMemoryXStream[T] = js.native // @TODO is it ok to change argument names? In TS this is labelOrSpy
//  override def debug(): JsMemoryXStream[T] = js.native
//}

//object XStream {
//  type FromInput[T] = js.Promise[T] | JsXStream[T] | js.Array[T] // | Observable[T] @TODO is this some sort of TS observable?
//
//  //  implicit def tuple2ToJsArray[T1, T2](tuple: (T1, T2)): js.Array[T1 | T2] = js.Array(tuple._1, tuple._2)
//
//  def jsArrayToTuple2[T1, T2](arr: js.Array[T1 | T2]): (T1, T2) = {
//    (arr(0).asInstanceOf[T1], arr(1).asInstanceOf[T2])
//  }
//  def jsArrayToTuple3[T1, T2, T3](arr: js.Array[T1 | T2 | T3]): (T1, T2, T3) = {
//    (arr(0).asInstanceOf[T1], arr(1).asInstanceOf[T2], arr(2).asInstanceOf[T3])
//  }
//  def jsArrayToTuple4[T1, T2, T3, T4](arr: js.Array[T1 | T2 | T3 | T4]): (T1, T2, T3, T4) = {
//    (arr(0).asInstanceOf[T1], arr(1).asInstanceOf[T2], arr(2).asInstanceOf[T3], arr(3).asInstanceOf[T4])
//  }
//  def jsArrayToTuple5[T1, T2, T3, T4, T5](arr: js.Array[T1 | T2 | T3 | T4 | T5]): (T1, T2, T3, T4, T5) = {
//    (arr(0).asInstanceOf[T1], arr(1).asInstanceOf[T2], arr(2).asInstanceOf[T3], arr(3).asInstanceOf[T4], arr(4).asInstanceOf[T5])
//  }
//
//  @inline
//  def create[T](producer: Option[Producer[T]] = None): XStream[T] = ??? //Bundle.JsXStreamOps.create[T](JSConverters.JSRichOption(producer).orUndefined) // @TODO How to make conversion automatic?
//
//  @inline
//  def createWithMemory[T](producer: Option[Producer[T]] = None): MemoryXStream[T] = ??? // Bundle.JsXStreamOps.createWithMemory[T](JSConverters.JSRichOption(producer).orUndefined) // @TODO How to make conversion automatic?
//
//  @inline
//  def never(): XStream[Nothing] = ??? // Bundle.JsXStreamOps.never()
//
//  @inline
//  def empty(): XStream[Nothing] = ??? // Bundle.JsXStreamOps.empty()
//
//  @inline
//  def throwError[T](error: T): XStream[T] = ??? //Bundle.JsXStreamOps.`throw`(error)
//
//  @inline
//  def from[T](input: FromInput[T]): XStream[T] = ??? //Bundle.JsXStreamOps.from(input)
//
//  @inline
//  def of[T](items: T*): XStream[T] = ??? // Bundle.JsXStreamOps.fromArray(JSConverters.JSRichGenTraversableOnce(items).toJSArray)
//
//  @inline
//  def fromSeq[T](seq: Seq[T]): XStream[T] = ??? // Bundle.JsXStreamOps.fromArray(JSConverters.JSRichGenTraversableOnce(seq).toJSArray)
//
//  @inline
//  def fromPromise[T](promise: js.Promise[T]): XStream[T] = ??? // Bundle.JsXStreamOps.fromPromise(promise)
//
//  //  def fromObservable[T](observable: Any): Stream[T] = js.native // @TODO What observable? Why??
//
//  @inline
//  def periodic(period: Int): XStream[Int] = ??? //Bundle.JsXStreamOps.periodic(period)
//
//  @inline
//  def merge[T](streams: XStream[T]*): XStream[T] = ??? //Bundle.JsXStreamOps.merge(streams: _*)
//
//  //  @inline
//  //  def merge[T1, T2](s1: XStream[T1], s2: XStream[T2]): XStream[T1 | T2] = jsStreamOps.merge(s1, s2)
//  //  @inline
//  //  def merge[T1, T2, T3](s1: XStream[T1], s2: XStream[T2], s3: XStream[T3]): XStream[T1 | T2 | T3] = jsStreamOps.merge(s1, s2, s3)
//  //  @inline
//  //  def merge[T1, T2, T3, T4](s1: XStream[T1], s2: XStream[T2], s3: XStream[T3], s4: XStream[T4]): XStream[T1 | T2 | T3 | T4] = jsStreamOps.merge(s1, s2, s3, s4)
//  //  @inline
//  //  def merge[T1, T2, T3, T4, T5](s1: XStream[T1], s2: XStream[T2], s3: XStream[T3], s4: XStream[T4], s5: XStream[T5]): XStream[T1 | T2 | T3 | T4 | T5] = jsStreamOps.merge(s1, s2, s3, s4, s5)
//
//  // @TODO
//
////  @inline
////  def combine[T1, T2](s1: JsXStream[T1], s2: JsXStream[T2]): JsXStream[(T1, T2)] = {
////    Bundle.JsXStreamOps.combine(s1, s2).map((arr: js.Array[T1 | T2]) => jsArrayToTuple2[T1, T2](arr))
////  }
////  @inline
////  def combine[T1, T2, T3](s1: JsXStream[T1], s2: JsXStream[T2], s3: JsXStream[T3]): JsXStream[(T1, T2, T3)] = {
////    Bundle.JsXStreamOps.combine(s1, s2, s3).map((arr: js.Array[T1 | T2 | T3]) => jsArrayToTuple3[T1, T2, T3](arr))
////  }
////  @inline
////  def combine[T1, T2, T3, T4](s1: JsXStream[T1], s2: JsXStream[T2], s3: JsXStream[T3], s4: JsXStream[T4]): JsXStream[(T1, T2, T3, T4)] = {
////    Bundle.JsXStreamOps.combine(s1, s2, s3, s4).map((arr: js.Array[T1 | T2 | T3 | T4]) => jsArrayToTuple4[T1, T2, T3, T4](arr))
////  }
////  @inline
////  def combine[T1, T2, T3, T4, T5](s1: JsXStream[T1], s2: JsXStream[T2], s3: JsXStream[T3], s4: JsXStream[T4], s5: JsXStream[T5]): JsXStream[(T1, T2, T3, T4, T5)] = {
////    Bundle.JsXStreamOps.combine(s1, s2, s3, s4, s5).map((arr: js.Array[T1 | T2 | T3 | T4 | T5]) => jsArrayToTuple5[T1, T2, T3, T4, T5](arr))
////  }
//}


