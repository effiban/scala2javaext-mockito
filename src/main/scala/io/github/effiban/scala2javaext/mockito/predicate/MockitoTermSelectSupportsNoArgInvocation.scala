package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2java.spi.contexts.TermSelectInferenceContext
import io.github.effiban.scala2java.spi.predicates.TermSelectSupportsNoArgInvocation

import scala.meta.{Term, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

object MockitoTermSelectSupportsNoArgInvocation extends TermSelectSupportsNoArgInvocation {

  override def apply(termSelect: Term.Select, context: TermSelectInferenceContext): Boolean =
    (context.maybeQualType, termSelect.qual, termSelect.name) match {
      case (Some(t"org.mockito.MockitoSugar"), _, q"mock") |
           (_, q"org.mockito.captor", q"ArgCaptor") |
           (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), _, q"isA") |
           (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), _, q"any") |
           (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), _, q"anyIterable") |
           (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), _, q"anyList") |
           (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), _, q"anySeq") |
           (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), _, q"anySet") |
           (Some(t"org.mockito.matchers.ArgumentMatchersSugar"), _, q"anyMap") => true
      case _ => false
    }
}
