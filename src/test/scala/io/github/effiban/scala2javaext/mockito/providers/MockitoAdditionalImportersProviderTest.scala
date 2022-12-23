package io.github.effiban.scala2javaext.mockito.providers

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

import scala.meta.XtensionQuasiquoteImporter

class MockitoAdditionalImportersProviderTest extends UnitTestSuite {

  test("provide() should return the import of 'org.mockito._'") {
    MockitoAdditionalImportersProvider.provide().structure shouldBe List(importer"org.mockito._").structure
  }

}
