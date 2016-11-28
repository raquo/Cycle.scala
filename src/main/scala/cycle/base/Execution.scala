package cycle.base

import scala.scalajs.js

@js.native
trait Execution[+Sos <: Sources, +Sis <: Sinks] extends js.Object {

  val sources: Sos = js.native

  val sinks: Sis = js.native

  def run(): DisposeFunction = js.native
}
