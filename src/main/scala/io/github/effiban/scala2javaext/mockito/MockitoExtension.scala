package io.github.effiban.scala2javaext.mockito

import io.github.effiban.scala2java.spi.Scala2JavaExtension
import io.github.effiban.scala2java.spi.predicates.{ImporterExcludedPredicate, TemplateInitExcludedPredicate}
import io.github.effiban.scala2java.spi.providers.AdditionalImportersProvider
import io.github.effiban.scala2java.spi.transformers.{ClassTransformer, DefnValToDeclVarTransformer, DefnValTransformer}
import io.github.effiban.scala2javaext.mockito.predicate.{MockitoImporterExcludedPredicate, MockitoTemplateInitExcludedPredicate}
import io.github.effiban.scala2javaext.mockito.providers.MockitoAdditionalImportersProvider
import io.github.effiban.scala2javaext.mockito.transformer.{MockitoClassTransformer, MockitoDefnValToDeclVarTransformer, MockitoDefnValTransformer}

import scala.meta.{Source, Term}

class MockitoExtension extends Scala2JavaExtension {

  override def shouldBeAppliedTo(source: Source): Boolean = source.collect {
    case mockitoQualifier@Term.Select(Term.Name("org"), Term.Name("mockito")) => mockitoQualifier
  }.nonEmpty


  override def additionalImportersProvider(): AdditionalImportersProvider = MockitoAdditionalImportersProvider

  override def importerExcludedPredicate(): ImporterExcludedPredicate = MockitoImporterExcludedPredicate

  override def classTransformer(): ClassTransformer = MockitoClassTransformer

  override def templateInitExcludedPredicate(): TemplateInitExcludedPredicate = MockitoTemplateInitExcludedPredicate

  override def defnValTransformer(): DefnValTransformer = MockitoDefnValTransformer

  override def defnValToDeclVarTransformer(): DefnValToDeclVarTransformer = MockitoDefnValToDeclVarTransformer
}
