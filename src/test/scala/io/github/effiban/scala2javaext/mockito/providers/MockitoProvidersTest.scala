package io.github.effiban.scala2javaext.mockito.providers

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

class MockitoProvidersTest extends UnitTestSuite {

  private val mockitoProviders = new MockitoProviders {}
  import mockitoProviders._

  test("additionalImportersProvider() should return MockitoAdditionalImportersProvider") {
    additionalImportersProvider() shouldBe MockitoAdditionalImportersProvider
  }
}
