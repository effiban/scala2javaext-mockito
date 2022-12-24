package io.github.effiban.scala2javaext.mockito

import io.github.effiban.scala2javaext.mockito.predicate.{MockitoImporterExcludedPredicate, MockitoTemplateInitExcludedPredicate}
import io.github.effiban.scala2javaext.mockito.providers.MockitoAdditionalImportersProvider
import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer._
import org.mockito.ArgumentMatchersSugar.any

import scala.meta.XtensionQuasiquoteTerm

class MockitoExtensionTest extends UnitTestSuite {

  private val mockitoExtension = new MockitoExtension
  import mockitoExtension._

  test("shouldBeAppliedIfContains() for 'org.mockito' should return true") {
    shouldBeAppliedIfContains(q"org.mockito") shouldBe true
  }

  test("shouldBeAppliedIfContains() for a non-mockito qualified name should return false") {
    shouldBeAppliedIfContains(q"org.othermock") shouldBe false
  }

  test("additionalImportersProvider() should return MockitoAdditionalImportersProvider") {
    any[String]
    additionalImportersProvider() shouldBe MockitoAdditionalImportersProvider
  }

  test("importerExcludedPredicate() should return MockitoImporterExcludedPredicate") {
    importerExcludedPredicate() shouldBe MockitoImporterExcludedPredicate
  }

  test("classTransformer() should return MockitoClassTransformer") {
    classTransformer() shouldBe MockitoClassTransformer
  }

  test("templateInitExcludedPredicate() should return MockitoTemplateInitExcludedPredicate") {
    templateInitExcludedPredicate() shouldBe MockitoTemplateInitExcludedPredicate
  }

  test("defnValTransformer() should return MockitoDefnValTransformer") {
    defnValTransformer() shouldBe MockitoDefnValTransformer
  }

  test("defnValToDeclVarTransformer() should return MockitoDefnValToDeclVarTransformer") {
    defnValToDeclVarTransformer() shouldBe MockitoDefnValToDeclVarTransformer
  }

  test("termApplyTypeToTermApplyTransformer() should return MockitoTermApplyTypeToTermApplyTransformer") {
    termApplyTypeToTermApplyTransformer() shouldBe MockitoTermApplyTypeToTermApplyTransformer
  }

  test("termSelectTransformer() should return MockitoTermSelectTransformer") {
    termSelectTransformer() shouldBe MockitoTermSelectTransformer
  }
}
