package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2java.spi.predicates.TermNameSupportsNoArgInvocation

import scala.meta.{Term, XtensionQuasiquoteTerm}

object MockitoTermNameSupportsNoArgInvocation extends TermNameSupportsNoArgInvocation {

  override def apply(termName: Term.Name): Boolean = termName match {
    case q"mock" |
         q"ArgCaptor" |
         q"isA" |
         q"any" |
         q"anyIterable" |
         q"anyList" |
         q"anySeq" |
         q"anySet" |
         q"anyMap" => true
    case _ => false
  }
}
