package com.raquo.cycle.base

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

// @TODO[Integrity] Use this self-type pattern? https://gist.github.com/odersky/56323c309a186cffe9af

@ScalaJSDefined
trait IsolatableSource[So <: IsolatableSource[_, Si], Si[_ <: js.Error]] extends js.Object {

  protected def isolateSource(source: So, scope: String): So

  protected def isolateSink[Err <: js.Error](sink: Si[Err], scope: String): Si[Err]
}

@js.native
trait Sources extends js.Object
