package xstream

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

@js.native
@JSImport("xstream", JSImport.Default)
object RawXStream extends js.Object {

  def create[T](producer: Producer[T]): XStream[T] = js.native

  def create[T](): XStream[T] = js.native

  def createWithMemory[T](producer: js.UndefOr[Producer[T]] = js.undefined): MemoryStream[T] = js.native

  def never(): XStream[Nothing] = js.native // @TODO is Nothing a correct type?

  def empty(): XStream[Nothing] = js.native // @TODO is Nothing a correct type?

  def `throw`[E](error: E): XStream[E] = js.native

  def of[T](values: T*): XStream[T] = js.native // @TODO is this correct signature for TS `(...items: Array<T>)`?

  def fromArray[T](array: js.Array[T]): XStream[T] = js.native

  def fromPromise[T](promise: js.Promise[T]): XStream[T] = js.native

  def fromObservable[T](observable: Any): XStream[T] = js.native // @TODO What observable? Why??

  def periodic(period: Int): XStream[Int] = js.native

  def merge[T](streams: XStream[T]*): XStream[T] = js.native

  def merge[T](streams: js.Array[XStream[T]]): XStream[T] = js.native // @TODO does this do what I think it does?

  // @TODO Add up to... 8? variations

  def combine[T](streams: XStream[T]*): XStream[js.Array[T]] = js.native // @TODO does this do what I think it does?

  def combine[T1, T2](stream1: XStream[T1], stream2: XStream[T2]): XStream[js.Array[T1 | T2]] = js.native

  def combine[T1, T2, T3](stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3]): XStream[js.Array[T1 | T2 | T3]] = js.native

  def combine[T1, T2, T3, T4](stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3], stream4: XStream[T4]): XStream[js.Array[T1 | T2 | T3 | T4]] = js.native

  def combine[T1, T2, T3, T4, T5](stream1: XStream[T1], stream2: XStream[T2], stream3: XStream[T3], stream4: XStream[T4], stream5: XStream[T5]): XStream[js.Array[T1 | T2 | T3 | T4 | T5]] = js.native
}
