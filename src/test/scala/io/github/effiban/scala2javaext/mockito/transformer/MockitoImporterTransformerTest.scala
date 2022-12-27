package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoImporterTransformer.transform

import scala.meta.{Importer, XtensionQuasiquoteImporter}

class MockitoImporterTransformerTest extends UnitTestSuite {

  private val Scenarios = Table(
    ("Input", "ExpectedOutput"),
    (importer"org.mockito.ArgumentMatchersSugar.argThat", importer"org.mockito.ArgumentMatchers.argThat"),
    (importer"org.mockito.ArgumentMatchersSugar.intThat", importer"org.mockito.ArgumentMatchers.argThat"),
    (importer"org.mockito.ArgumentMatchersSugar.any", importer"org.mockito.ArgumentMatchers.any"),
    (importer"org.mockito.ArgumentMatchersSugar.anyList", importer"org.mockito.ArgumentMatchers.any"),
    (importer"org.mockito.ArgumentMatchersSugar.eqTo", importer"org.mockito.ArgumentMatchers.eq"),
    (importer"org.mockito.ArgumentMatchersSugar._", importer"org.mockito.ArgumentMatchers._"),
    (importer"org.mockito.MockitoSugar.mock", importer"org.mockito.Mockito.mock"),
    (importer"org.mockito.MockitoSugar.spy", importer"org.mockito.Mockito.spy"),
    (importer"org.mockito.MockitoSugar._", importer"org.mockito.Mockito._"),
    (importer"org.mockito.captor.Captor", importer"org.mockito.ArgumentCaptor"),
    (importer"org.mockito.captor.ArgCaptor", importer"org.mockito.ArgumentCaptor"),
    (importer"aaa.bbb.ccc", importer"aaa.bbb.ccc")
  )

  forAll(Scenarios) {
    (input: Importer, expectedOutput: Importer) =>
      test(s"'$input' should be transformed to '$expectedOutput'") {
        transform(input).structure shouldBe expectedOutput.structure
      }
  }
}
