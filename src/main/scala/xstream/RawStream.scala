package xstream

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@js.native
class RawStream[T] extends js.Object {

  def addListener(listener: RawListener[T]): Unit = js.native

  def removeListener(listener: RawListener[T]): Unit = js.native

  def subscribe(listener: RawListener[T]): RawSubscription[T] = js.native

  def map[U](project: js.Function1[T, U]): RawStream[U] = js.native

  def mapTo[U](projectedValue: U): RawStream[U] = js.native

  def filter(passes: js.Function1[T, Boolean]): RawStream[T] = js.native

  def take(amount: Int): RawStream[T] = js.native

  def drop(amount: Int): RawStream[T] = js.native

  def last(): RawStream[T] = js.native

  def startWith(initial: T): RawMemoryStream[T] = js.native

  def endWhen(other: RawStream[_]): RawStream[T] = js.native

  def fold[R](accumulate: js.Function2[R, T, R], seed: R): RawMemoryStream[R] = js.native

  // @TODO should `E` type exist here? In Typescript it's `any`
  def replaceError[E](replace: js.Function1[E, RawStream[T]]): RawStream[T] = js.native

  // @TODO. This only makes sense if T is Stream[R]. How do enforce this limitation in Scala?
  def flatten[R]: RawStream[R] = js.native

  def compose[U](operator: js.Function1[RawStream[T], RawStream[U]]): RawStream[U] = js.native

  def compose[U](operator: js.Function1[RawStream[T], RawMemoryStream[U]]): RawMemoryStream[U] = js.native

  def remember(): RawMemoryStream[T] = js.native

  def debug(spy: js.Function1[T, Unit]): RawStream[T] = js.native

  def debug(label: String): RawStream[T] = js.native

  def debug(): RawStream[T] = js.native

  def imitate(target: RawStream[T]): Unit = js.native

  def shamefullySendNext(value: T): Unit = js.native

  def shamefullySendError[E](error: E): Unit = js.native

  def shamefullySendComplete(): Unit = js.native

  def setDebugListener(listener: RawListener[T]): Unit = js.native
}

@js.native
@JSImport("xstream", JSImport.Default)
object RawStream extends js.Object {

  type FromInput[T] = js.Promise[T] | RawStream[T] | js.Array[T] // | Observable[T] @TODO Do we care about ES6 Observables?

  def create[T](producer: js.UndefOr[RawProducer[T]] = js.undefined): RawStream[T] = js.native

  def createWithMemory[T](producer: js.UndefOr[RawProducer[T]] = js.undefined): RawMemoryStream[T] = js.native

  def never(): RawStream[Nothing] = js.native // @TODO is Nothing a correct type?

  def empty(): RawStream[Nothing] = js.native // @TODO is Nothing a correct type?

  def `throw`[E](error: E): RawStream[E] = js.native

  def from[T](input: FromInput[T]): RawStream[T] = js.native

  //  def of[T](items: js.Array[T]*): Stream[T] = js.native // @TODO is this correct signature for TS `(...items: Array<T>)`?

  def fromArray[T](array: js.Array[T]): RawStream[T] = js.native

  def fromPromise[T](promise: js.Promise[T]): RawStream[T] = js.native

  def fromObservable[T](observable: Any): RawStream[T] = js.native // @TODO What observable? Why??

  def periodic(period: Int): RawStream[Int] = js.native


  def merge[T](streams: RawStream[T]*): RawStream[T] = js.native // @TODO does this do what I think it does?

  //  def merge[T](streams: js.Array[XStream[T]]): XStream[T] = js.native // @TODO does this do what I think it does?

  //  def merge[T1, T2](stream1: RawStream[T1], stream2: RawStream[T2]): RawStream[T1 | T2] = js.native

  //  def merge[T1, T2, T3](stream1: RawStream[T1], stream2: RawStream[T2], stream3: RawStream[T3]): RawStream[T1 | T2 | T3] = js.native

  //  def merge[T1, T2, T3, T4](stream1: RawStream[T1], stream2: RawStream[T2], stream3: RawStream[T3], stream4: RawStream[T4]): RawStream[T1 | T2 | T3 | T4] = js.native

  //  def merge[T1, T2, T3, T4, T5](stream1: RawStream[T1], stream2: RawStream[T2], stream3: RawStream[T3], stream4: RawStream[T4], stream5: RawStream[T5]): RawStream[T1 | T2 | T3 | T4 | T5] = js.native


  // @TODO Add up to... 8? variations

  def combine[T](streams: RawStream[T]*): RawStream[js.Array[T]] = js.native // @TODO does this do what I think it does?

  def combine[T1, T2](stream1: RawStream[T1], stream2: RawStream[T2]): RawStream[js.Array[T1 | T2]] = js.native

  def combine[T1, T2, T3](stream1: RawStream[T1], stream2: RawStream[T2], stream3: RawStream[T3]): RawStream[js.Array[T1 | T2 | T3]] = js.native

  def combine[T1, T2, T3, T4](stream1: RawStream[T1], stream2: RawStream[T2], stream3: RawStream[T3], stream4: RawStream[T4]): RawStream[js.Array[T1 | T2 | T3 | T4]] = js.native

  def combine[T1, T2, T3, T4, T5](stream1: RawStream[T1], stream2: RawStream[T2], stream3: RawStream[T3], stream4: RawStream[T4], stream5: RawStream[T5]): RawStream[js.Array[T1 | T2 | T3 | T4 | T5]] = js.native
}
