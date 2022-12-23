package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2java.spi.predicates.ImporterExcludedPredicate

import scala.meta.{Importer, XtensionQuasiquoteImporter, XtensionQuasiquoteTerm}

object MockitoImporterExcludedPredicate extends ImporterExcludedPredicate {

  override def apply(importer: Importer): Boolean = importer match {
    case importer"org.mockito.ArgumentMatcherSugar" |
         importer"org.mockito.IdiomaticMockito" |
         importer"org.mockito.MockitoSugar" |
         Importer(q"org.mockito.integrations.scalatest", _) => true
    case _ => false
  }
}
