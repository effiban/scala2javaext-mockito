package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2java.spi.predicates.{ExtendedPredicates, TemplateInitExcludedPredicate, TermSelectHasApplyMethod, TermSelectSupportsNoArgInvocation}

trait MockitoPredicates extends ExtendedPredicates {

  override def termSelectHasApplyMethod(): TermSelectHasApplyMethod = MockitoTermSelectHasApplyMethod

  override def termSelectSupportsNoArgInvocation(): TermSelectSupportsNoArgInvocation = MockitoTermSelectSupportsNoArgInvocation
}
