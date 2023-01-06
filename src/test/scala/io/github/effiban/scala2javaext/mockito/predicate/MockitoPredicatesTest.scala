package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

class MockitoPredicatesTest extends UnitTestSuite {

  private val mockitoPredicates = new MockitoPredicates {}
  import mockitoPredicates._

  test("importerExcludedPredicate() should return MockitoImporterExcludedPredicate") {
    importerExcludedPredicate() shouldBe MockitoImporterExcludedPredicate
  }

  test("templateInitExcludedPredicate() should return MockitoTemplateInitExcludedPredicate") {
    templateInitExcludedPredicate() shouldBe MockitoTemplateInitExcludedPredicate
  }
}
