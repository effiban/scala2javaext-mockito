package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2java.spi.predicates.{ExtendedPredicates, ImporterExcludedPredicate, TemplateInitExcludedPredicate, TermNameHasApplyMethod, TermNameSupportsNoArgInvocation}

trait MockitoPredicates extends ExtendedPredicates {

  override def importerExcludedPredicate(): ImporterExcludedPredicate = MockitoImporterExcludedPredicate

  override def templateInitExcludedPredicate(): TemplateInitExcludedPredicate = MockitoTemplateInitExcludedPredicate

  override def termNameHasApplyMethod(): TermNameHasApplyMethod = MockitoTermNameHasApplyMethod

  override def termNameSupportsNoArgInvocation(): TermNameSupportsNoArgInvocation = MockitoTermNameSupportsNoArgInvocation
}
