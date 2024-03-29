package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

class MockitoTypeInferrersTest extends UnitTestSuite {

  private val mockitoTypeInferrers = new MockitoTypeInferrers {}
  import mockitoTypeInferrers._

  test("applyDeclDefInferrer() should return MockitoApplyDeclDefInferrer") {
    applyDeclDefInferrer() shouldBe MockitoApplyDeclDefInferrer
  }
}
