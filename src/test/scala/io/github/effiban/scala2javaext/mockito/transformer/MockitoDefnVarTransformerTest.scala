package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.entities.JavaScope
import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoDefnVarTransformer.transform

import scala.meta.XtensionQuasiquoteTerm

class MockitoDefnVarTransformerTest extends UnitTestSuite {

  test("transform() for a spy in class scope with explicit type and initializer, and 'final', should add the '@Spy' annotation") {
    val inputDefnVar = q"private final var mySpy: Foo = spy[Foo](new Foo(2))"

    val expectedOutputDefnVar =
      q"""
      @Spy
      private final var mySpy: Foo = new Foo(2)
      """

    transform(inputDefnVar, JavaScope.Class).structure shouldBe expectedOutputDefnVar.structure
  }

  test("transform() for a spy in class scope with explicit type and initializer, without 'final', should add the '@Spy' annotation") {
    val inputDefnVar = q"private var mySpy: Foo = spy[Foo](new Foo(2))"

    val expectedOutputDefnVar =
      q"""
      @Spy
      private var mySpy: Foo = new Foo(2)
      """

    transform(inputDefnVar, JavaScope.Class).structure shouldBe expectedOutputDefnVar.structure
  }

  test("transform() for a spy in class scope with an implicit type should add the type and the '@Spy' annotation") {
    val inputDefnVar = q"private var mySpy = spy[Foo](new Foo(2))"

    val expectedOutputDefnVar =
      q"""
      @Spy
      private var mySpy: Foo = new Foo(2)
      """

    transform(inputDefnVar, JavaScope.Class).structure shouldBe expectedOutputDefnVar.structure
  }

  test("transform() for a spy in block scope should return unchanged") {
    val defnVar = q"private var mySpy = spy[Foo](new Foo(2))"

    transform(defnVar, JavaScope.Block).structure shouldBe defnVar.structure
  }

  test("transform() for a mock in class scope should return unchanged") {
    val defnVar = q"private var myMock = mock[Foo]()"

    transform(defnVar, JavaScope.Class).structure shouldBe defnVar.structure
  }
}
