package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2java.spi.predicates.TermSelectHasApplyMethod

import scala.meta.{Term, XtensionQuasiquoteTerm}

object MockitoTermSelectHasApplyMethod extends TermSelectHasApplyMethod {

  override def apply(termSelect: Term.Select): Boolean = termSelect match {
    case q"org.mockito.captor.ArgCaptor" => true
    case _ => false
  }
}
