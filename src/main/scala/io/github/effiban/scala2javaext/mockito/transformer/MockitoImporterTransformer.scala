package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.transformers.ImporterTransformer

import scala.meta.{Importee, Importer, Term, XtensionQuasiquoteImportee, XtensionQuasiquoteImporter, XtensionQuasiquoteTerm}

object MockitoImporterTransformer extends ImporterTransformer {

  override def transform(theImporter: Importer): Importer = {
    theImporter match {
      case Importer(ref@q"org.mockito.ArgumentMatchersSugar", importee :: Nil) => transformArgumentMatcher(ref, importee)
      case Importer(q"org.mockito.MockitoSugar", importee :: Nil) => Importer(q"org.mockito.Mockito", List(importee))
      case Importer(q"org.mockito.captor", _ :: Nil) => importer"org.mockito.ArgumentCaptor"
      case other => other
    }
  }

  private def transformArgumentMatcher(ref: Term.Ref, theImportee: Importee): Importer = {
    val newRef = q"org.mockito.ArgumentMatchers"
    theImportee match {
      case Importee.Name(nm) if nm.value.startsWith("any") => Importer(newRef, List(importee"any"))
      case importee"eqTo" => Importer(newRef, List(importee"eq"))
      case importee"isA" => Importer(newRef, List(importee"isA"))
      case Importee.Name(nm) if nm.value.endsWith("That") => Importer(newRef, List(importee"argThat"))
      case Importee.Wildcard() => Importer(newRef, List(Importee.Wildcard()))
      case anImportee => Importer(ref, List(anImportee))
    }
  }
}
