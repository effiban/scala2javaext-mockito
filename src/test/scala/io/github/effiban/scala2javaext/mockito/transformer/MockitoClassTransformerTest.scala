package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoClassTransformer.transform

import scala.meta.XtensionQuasiquoteTerm

class MockitoClassTransformerTest extends UnitTestSuite {

  test("transform()") {
    val inputClass =
      q"""
      class MyClass {
        val x = 3
      }
      """

    val expectedOutputClass =
      q"""
      @org.junit.jupiter.api.extension.ExtendWith(classOf[org.mockito.junit.jupiter.MockitoExtension])
      class MyClass {
        val x = 3
      }
      """

    transform(inputClass).structure shouldBe expectedOutputClass.structure
  }
}
