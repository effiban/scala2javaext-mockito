package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

class MockitoTransformersTest extends UnitTestSuite {

  private val mockitoTransformers = new MockitoTransformers {}
  import mockitoTransformers._

  test("importerTransformer() should return MockitoImporterTransformer") {
    importerTransformer() shouldBe MockitoImporterTransformer
  }

  test("classTransformer() should return MockitoClassTransformer") {
    classTransformer() shouldBe MockitoClassTransformer
  }

  test("defnVarTransformer() should return MockitoDefnVarTransformer") {
    defnVarTransformer() shouldBe MockitoDefnVarTransformer
  }

  test("defnVarToDeclVarTransformer() should return MockitoDefnVarToDeclVarTransformer") {
    defnVarToDeclVarTransformer() shouldBe MockitoDefnVarToDeclVarTransformer
  }

  test("termApplyTransformer() should return MockitoTermApplyTransformer") {
    termApplyTransformer() shouldBe MockitoTermApplyTransformer
  }
}
