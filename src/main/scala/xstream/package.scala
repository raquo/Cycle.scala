import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}

package object xstream {

  implicit class RichStream[T] (val stream: XStream[T]) extends AnyVal {

    def addListener(listener: Listener[T]): Unit =
      stream.addListener(listener)

    def removeListener(listener: Listener[T]): Unit =
      stream.removeListener(listener)

    def subscribe(listener: Listener[T]): Subscription[T] =
      stream.subscribe(listener)

    def map[U](project: T => U): XStream[U] = stream.mapJs(project)

    def filter(passes: T => Boolean): XStream[T] =
      stream.filter(passes)

    def fold[R](accumulate: (R, T) => R, seed: R): MemoryStream[R] =
      stream.fold(accumulate, seed)

    def replaceError[E](replace: E => XStream[T]): XStream[T] =
      stream.replaceError[E]((error: E) => replace(error))

    def compose[U](operator: XStream[T] => XStream[U]): XStream[U] = {
      stream.compose[U]((someRawStream: XStream[T]) => operator(someRawStream))
    }

    def debug(spy: T => Unit): XStream[T] =
      stream.debug(spy)

    def debugger(): XStream[T] =
      stream.debug((value: T) => js.debugger())

    def setDebugListener(listener: Listener[T]): Unit =
      stream.setDebugListener(listener)
  }

  implicit class MetaStream[T] (val streamOfStreams: XStream[XStream[T]]) extends AnyVal {

    def flatten: XStream[T] = streamOfStreams.flattenJs[T]()
  }

  implicit class TupleStream2[T1, T2](val tupleStream: XStream[(T1, T2)]) extends AnyVal {

    @inline def map[U](project: (T1, T2) => U): XStream[U] =
      tupleStream.mapJs(project.tupled)

    @inline def filter(passes: (T1, T2) => Boolean): XStream[(T1, T2)] =
      tupleStream.filter(passes.tupled)

    @inline def debug(spy: (T1, T2) => Any): XStream[(T1, T2)] =
      tupleStream.debug(spy.tupled)
  }

  implicit class TupleStream3[T1, T2, T3](val tupleStream: XStream[(T1, T2, T3)]) extends AnyVal {

    @inline def map[U](project: (T1, T2, T3) => U): XStream[U] =
      tupleStream.mapJs(project.tupled)

    @inline def filter(passes: (T1, T2, T3) => Boolean): XStream[(T1, T2, T3)] =
      tupleStream.filter(passes.tupled)

    @inline def debug(spy: (T1, T2, T3) => Any): XStream[(T1, T2, T3)] =
      tupleStream.debug(spy.tupled)
  }

  implicit class TupleStream4[T1, T2, T3, T4](val tupleStream: XStream[(T1, T2, T3, T4)]) extends AnyVal {

    @inline def map[U](project: (T1, T2, T3, T4) => U): XStream[U] =
      tupleStream.mapJs(project.tupled)

    @inline def filter(passes: (T1, T2, T3, T4) => Boolean): XStream[(T1, T2, T3, T4)] =
      tupleStream.filter(passes.tupled)

    @inline def debug(spy: (T1, T2, T3, T4) => Any): XStream[(T1, T2, T3, T4)] =
      tupleStream.debug(spy.tupled)
  }
}
