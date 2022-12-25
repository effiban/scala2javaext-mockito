package io.github.effiban.scala2javaext.mockito

import io.github.effiban.scala2java.spi.Scala2JavaExtension
import io.github.effiban.scala2java.spi.predicates.{ImporterExcludedPredicate, TemplateInitExcludedPredicate}
import io.github.effiban.scala2java.spi.providers.AdditionalImportersProvider
import io.github.effiban.scala2java.spi.transformers._
import io.github.effiban.scala2javaext.mockito.predicate.{MockitoImporterExcludedPredicate, MockitoTemplateInitExcludedPredicate}
import io.github.effiban.scala2javaext.mockito.providers.MockitoAdditionalImportersProvider
import io.github.effiban.scala2javaext.mockito.transformer._

import scala.meta.{Term, XtensionQuasiquoteTerm}

class MockitoExtension extends Scala2JavaExtension {

  override def shouldBeAppliedIfContains(termSelect: Term.Select): Boolean =
    q"org.mockito".structure == termSelect.structure

  override def additionalImportersProvider(): AdditionalImportersProvider = MockitoAdditionalImportersProvider

  override def importerExcludedPredicate(): ImporterExcludedPredicate = MockitoImporterExcludedPredicate

  override def classTransformer(): ClassTransformer = MockitoClassTransformer

  override def templateInitExcludedPredicate(): TemplateInitExcludedPredicate = MockitoTemplateInitExcludedPredicate

  override def defnValTransformer(): DefnValTransformer = MockitoDefnValTransformer

  override def defnValToDeclVarTransformer(): DefnValToDeclVarTransformer = MockitoDefnValToDeclVarTransformer

  override def termApplyTypeToTermApplyTransformer(): TermApplyTypeToTermApplyTransformer = MockitoTermApplyTypeToTermApplyTransformer

  override def termApplyTransformer(): TermApplyTransformer = MockitoTermApplyTransformer

  override def termSelectTransformer(): TermSelectTransformer = MockitoTermSelectTransformer

  override def typeNameTransformer(): TypeNameTransformer = MockitoTypeNameTransfomer
}
