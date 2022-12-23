package io.github.effiban.scala2javaext.mockito.providers

import io.github.effiban.scala2java.spi.providers.AdditionalImportersProvider

import scala.meta.{Importer, XtensionQuasiquoteImporter}

object MockitoAdditionalImportersProvider extends AdditionalImportersProvider {
  override def provide(): List[Importer] = List(importer"org.mockito._")
}
