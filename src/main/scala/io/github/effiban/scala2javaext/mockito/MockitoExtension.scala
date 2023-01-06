package io.github.effiban.scala2javaext.mockito

import io.github.effiban.scala2java.spi.Scala2JavaExtension
import io.github.effiban.scala2javaext.mockito.predicate.MockitoPredicates
import io.github.effiban.scala2javaext.mockito.providers.MockitoProviders
import io.github.effiban.scala2javaext.mockito.transformer._

import scala.meta.{Term, XtensionQuasiquoteTerm}

class MockitoExtension extends Scala2JavaExtension
  with MockitoPredicates
  with MockitoProviders
  with MockitoTransformers {

  override def shouldBeAppliedIfContains(termSelect: Term.Select): Boolean =
    q"org.mockito".structure == termSelect.structure
}
