package xstream

import XStream.fromRawStream

class TupleStream3[T1, T2, T3](rawStream: RawStream[(T1, T2, T3)])
  extends XStream[(T1, T2, T3)](rawStream) {

  @inline def map[U](project: (T1, T2, T3) => U): XStream[U] =
    fromRawStream(rawStream.map(project.tupled))

  @inline def map[U1, U2](project: (T1, T2, T3) => (U1, U2)): TupleStream2[U1, U2] =
    fromRawStream(rawStream.map(project.tupled))

  @inline  def map[U1, U2, U3](project: (T1, T2, T3) => (U1, U2, U3)): TupleStream3[U1, U2, U3] =
    fromRawStream(rawStream.map(project.tupled))

  @inline def map[U1, U2, U3, U4](project: (T1, T2, T3) => (U1, U2, U3, U4)): TupleStream4[U1, U2, U3, U4] =
    fromRawStream(rawStream.map(project.tupled))

  @inline def filter(passes: (T1, T2, T3) => Boolean): TupleStream3[T1, T2, T3] =
    fromRawStream(rawStream.filter(passes.tupled))

  @inline override def debug(): TupleStream3[T1, T2, T3] =
    fromRawStream(rawStream.debug())

  @inline override def debug(label: String): TupleStream3[T1, T2, T3] =
    fromRawStream(rawStream.debug(label))

  @inline def debug(spy: (T1, T2, T3) => Unit): TupleStream3[T1, T2, T3] =
    fromRawStream(rawStream.debug(spy.tupled))
}
