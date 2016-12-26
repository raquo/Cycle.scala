package com.raquo.cycle.isolate

import com.raquo.cycle.base.{Sinks, Sources}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("@cycle/isolate", JSImport.Default)
object RawIsolate extends js.Object {

  def apply[Sis <: Sinks, Sos <: Sources](
    main: js.Function1[Sos, Sis],
    scope: js.UndefOr[String] = js.undefined
  ): js.Function1[Sos, Sis] = js.native
}

object Isolate {

  @inline
  def apply[Sis <: Sinks, Sos <: Sources](main: Sos => Sis): Sos => Sis = RawIsolate(main)

  @inline
  def apply[Sis <: Sinks, Sos <: Sources](main: Sos => Sis, scope: String): Sos => Sis = RawIsolate(main, scope)
}
