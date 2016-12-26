package com.raquo.cycle.http

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("@cycle/http", JSImport.Namespace)
object RawCycleHTTP extends js.Object {
  def makeHTTPDriver(): RawHTTPDriver = js.native
}

object CycleHTTP {
  def makeHTTPDriver(): HTTPDriver = new HTTPDriver(RawCycleHTTP.makeHTTPDriver())
}
