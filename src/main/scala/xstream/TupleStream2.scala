package xstream

import XStream.{fromRawStream, fromRawMemoryStream}

class TupleStream2[T1, T2](rawStream: RawStream[(T1, T2)])
  extends XStream[(T1, T2)](rawStream) {

  @inline def map[U](project: (T1, T2) => U): XStream[U] =
    fromRawStream(rawStream.map(project.tupled))

  @inline def map[U1, U2](project: (T1, T2) => (U1, U2)): TupleStream2[U1, U2] =
    fromRawStream(rawStream.map(project.tupled))

  @inline def map[U1, U2, U3](project: (T1, T2) => (U1, U2, U3)): TupleStream3[U1, U2, U3] =
    fromRawStream(rawStream.map(project.tupled))

  @inline def map[U1, U2, U3, U4](project: (T1, T2) => (U1, U2, U3, U4)): TupleStream4[U1, U2, U3, U4] =
    fromRawStream(rawStream.map(project.tupled))

  @inline def filter(passes: (T1, T2) => Boolean): TupleStream2[T1, T2] =
    fromRawStream(rawStream.filter(passes.tupled))

  @inline def debug(spy: (T1, T2) => Unit): TupleStream2[T1, T2] =
    fromRawStream(rawStream.debug(spy.tupled))
}

class TupleMemoryStream2[T1, T2](override val rawStream: RawMemoryStream[(T1, T2)])
  extends MemoryStream[(T1, T2)](rawStream) {

  @inline def map[U](project: (T1, T2) => U): MemoryStream[U] =
    fromRawMemoryStream(rawStream.map(project.tupled))

  @inline def map[U1, U2](project: (T1, T2) => (U1, U2)): TupleMemoryStream2[U1, U2] =
    fromRawMemoryStream(rawStream.map(project.tupled))

  @inline def map[U1, U2, U3](project: (T1, T2) => (U1, U2, U3)): TupleMemoryStream3[U1, U2, U3] =
    fromRawMemoryStream(rawStream.map(project.tupled))

  @inline def map[U1, U2, U3, U4](project: (T1, T2) => (U1, U2, U3, U4)): TupleMemoryStream4[U1, U2, U3, U4] =
    fromRawMemoryStream(rawStream.map(project.tupled))

  @inline def filter(passes: (T1, T2) => Boolean): TupleStream2[T1, T2] =
    fromRawStream(rawStream.filter(passes.tupled))

  @inline def debug(spy: (T1, T2) => Unit): TupleMemoryStream2[T1, T2] =
    fromRawMemoryStream(rawStream.debug(spy.tupled))
}
