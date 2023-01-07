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
    val javaRef = q"org.mockito.ArgumentMatchers"
    theImportee match {
      case Importee.Name(nm) if nm.value.startsWith("any") => Importer(javaRef, List(importee"any"))
      case importee"eqTo" => Importer(javaRef, List(importee"eq"))
      case anImportee@(importee"isA" |
                       importee"refEq" |
                       importee"same") => Importer(javaRef, List(anImportee))
      case Importee.Name(nm) if nm.value.endsWith("That") => Importer(javaRef, List(importee"argThat"))
      case Importee.Wildcard() => Importer(javaRef, List(Importee.Wildcard()))
      case anImportee => Importer(ref, List(anImportee))
    }
  }
}
