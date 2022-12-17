package io.github.effiban.scala2javaext.mockito

import io.github.effiban.scala2java.spi.Scala2JavaExtension

import scala.meta.{Source, Term}

object MockitoExtension extends Scala2JavaExtension {
  override def shouldBeAppliedTo(source: Source): Boolean = source.collect {
    case mockitoQualifier@Term.Select(Term.Name("org"), Term.Name("mockito")) => mockitoQualifier
  }.nonEmpty
}
