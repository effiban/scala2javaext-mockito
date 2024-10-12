package io.github.effiban.scala2javaext.mockito.common

import scala.meta.XtensionQuasiquoteMod

object MockitoJavaAnnotations {
  val Mock = mod"@org.mockito.Mock"
  val Spy = mod"@org.mockito.Spy"
  val Captor = mod"@org.mockito.Captor"
}
