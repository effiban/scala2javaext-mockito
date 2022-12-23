package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

import scala.meta.{Importer, XtensionQuasiquoteImporter}

class MockitoImporterExcludedPredicateTest extends UnitTestSuite {

  private val ImporterScenarios = Table(
    ("Importer", "ExpectedExcluded"),
    (importer"org.mockito.ArgumentMatcherSugar", true),
    (importer"org.mockito.IdiomaticMockito", true),
    (importer"org.mockito.MockitoSugar", true),
    (importer"org.mockito.integrations.scalatest.ResetMocksAfterEachTest", true),
    (importer"org.mockito.integrations.scalatest.ResetMocksAfterEachAsyncTest", true),
    (importer"org.mockito.Mock", false),
    (importer"org.mockito.Mockito", false),
    (importer"org.mockito.Spy", false)
  )

  forAll(ImporterScenarios) { case (importer: Importer, expectedExcluded: Boolean) =>
    test(s"$importer should ${if (!expectedExcluded) "not "}be excluded") {
      MockitoImporterExcludedPredicate(importer) shouldBe expectedExcluded
    }
  }

}
