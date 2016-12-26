package com.raquo.cycle.http

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}

@ScalaJSDefined
class Attachment (
  val name: String,
  val path: js.UndefOr[String] = js.undefined,
  val filename: js.UndefOr[String] = js.undefined
) extends js.Object

@ScalaJSDefined
class AgentOptions (
  val key: String,
  val cert: String
) extends js.Object

@ScalaJSDefined
class RequestOptions (
  val url: String,
  val method: js.UndefOr[String] = js.undefined,
  val query: js.UndefOr[js.Object] = js.undefined,
  val send: js.UndefOr[js.Object] = js.undefined,
  val headers: js.UndefOr[js.Object] = js.undefined,
  val accept: js.UndefOr[String] = js.undefined,
  @JSName("type")
  val contentType: js.UndefOr[String] = js.undefined,
  val user: js.UndefOr[String] = js.undefined,
  val password: js.UndefOr[String] = js.undefined,
  val field: js.UndefOr[js.Object] = js.undefined,
  val progress: js.UndefOr[Boolean] = js.undefined,
  val attach: js.UndefOr[js.Array[Attachment]] = js.undefined,
  val agent: js.UndefOr[AgentOptions] = js.undefined,
  val withCredentials: js.UndefOr[Boolean] = js.undefined,
  val redirects: js.UndefOr[Int] = js.undefined,
  val category: js.UndefOr[String] = js.undefined,
  val `lazy`: js.UndefOr[Boolean] = js.undefined
) extends js.Object
