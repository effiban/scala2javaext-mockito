package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2java.spi.predicates.TermNameHasApplyMethod

import scala.meta.{Term, XtensionQuasiquoteTerm}

object MockitoTermNameHasApplyMethod extends TermNameHasApplyMethod {

  override def apply(termName: Term.Name): Boolean = termName match {
    case q"ArgCaptor" => true
    case _ => false
  }
}
