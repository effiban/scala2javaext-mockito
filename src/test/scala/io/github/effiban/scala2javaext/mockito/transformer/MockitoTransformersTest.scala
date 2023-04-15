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

  test("defnValTransformer() should return MockitoDefnValTransformer") {
    defnValTransformer() shouldBe MockitoDefnValTransformer
  }

  test("defnValToDeclVarTransformer() should return MockitoDefnValToDeclVarTransformer") {
    defnValToDeclVarTransformer() shouldBe MockitoDefnValToDeclVarTransformer
  }

  test("termApplyTransformer() should return MockitoTermApplyTransformer") {
    termApplyTransformer() shouldBe MockitoTermApplyTransformer
  }
}
