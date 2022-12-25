package io.github.effiban.scala2javaext.mockito.common

import scala.meta.XtensionQuasiquoteMod

object MockitoAnnotations {
  val Mock = mod"@Mock"
  val Spy = mod"@Spy"
  val Captor = mod"@Captor"
  /** A temporary synthetic annotation to avoid clashing with the Scala 'Captor' type until all transformations are complete  */
  val JavaCaptor = mod"@JavaCaptor"
}
