package io.github.effiban.scala2javaext.mockito

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

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
}
