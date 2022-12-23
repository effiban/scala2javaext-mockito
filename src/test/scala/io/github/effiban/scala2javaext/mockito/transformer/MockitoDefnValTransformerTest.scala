package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.entities.JavaScope
import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoDefnValTransformer.transform

import scala.meta.XtensionQuasiquoteTerm

class MockitoDefnValTransformerTest extends UnitTestSuite {

  test("transform() for a spy in class scope with explicit type and initializer, should add the '@Spy' annotation") {
    val inputDefnVal = q"private val mySpy: Foo = spy[Foo](new Foo(2))"

    val expectedOutputDefnVal =
      q"""
      @Spy
      private val mySpy: Foo = new Foo(2)
      """

    transform(inputDefnVal, JavaScope.Class).structure shouldBe expectedOutputDefnVal.structure
  }

  test("transform() for a spy in class scope with an implicit type should add the type and the '@Spy' annotation") {
    val inputDefnVal = q"private val mySpy = spy[Foo](new Foo(2))"

    val expectedOutputDefnVal =
      q"""
      @Spy
      private val mySpy: Foo = new Foo(2)
      """

    transform(inputDefnVal, JavaScope.Class).structure shouldBe expectedOutputDefnVal.structure
  }

  test("transform() for a spy in block scope should return unchanged") {
    val defnVal = q"private val mySpy = spy[Foo](new Foo(2))"

    transform(defnVal, JavaScope.Block).structure shouldBe defnVal.structure
  }

  test("transform() for a mock in class scope should return unchanged") {
    val defnVal = q"private val myMock = mock[Foo]"

    transform(defnVal, JavaScope.Class).structure shouldBe defnVal.structure
  }
}
