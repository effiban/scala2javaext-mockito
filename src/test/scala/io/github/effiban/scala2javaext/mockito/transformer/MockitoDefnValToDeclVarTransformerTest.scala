package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.entities.JavaScope
import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoDefnValToDeclVarTransformer.transform

import scala.meta.XtensionQuasiquoteTerm

class MockitoDefnValToDeclVarTransformerTest extends UnitTestSuite {

  test("transform() for a mock in class scope with explicit type should return a 'var' annotated with '@Mock'") {
    val defnVal = q"private val myMock: Foo = mock[Foo]"

    val expectedDeclVar =
      q"""
      @Mock
      private var myMock: Foo
      """

    transform(defnVal, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a mock in class scope with an implicit type should return a 'var' annotated with '@Mock'") {
    val defnVal = q"private val myMock = mock[Foo]"

    val expectedDeclVar =
      q"""
      @Mock
      private var myMock: Foo
      """

    transform(defnVal, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a spy in class scope with explicit type should return a 'var' annotated with '@Spy'") {
    val defnVal = q"private val mySpy: Foo = spy[Foo]"

    val expectedDeclVar =
      q"""
      @Spy
      private var mySpy: Foo
      """

    transform(defnVal, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a spy in class scope with an implicit type should return a 'var' annotated with '@Spy'") {
    val defnVal = q"private val mySpy = spy[Foo]"

    val expectedDeclVar =
      q"""
      @Spy
      private var mySpy: Foo
      """

    transform(defnVal, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a captor in class scope with explicit type should return a 'var' annotated with '@Captor'") {
    val defnVal = q"private val myCaptor: Captor[Foo] = ArgCaptor[Foo]"

    val expectedDeclVar =
      q"""
      @Captor
      private var myCaptor: Captor[Foo]
      """

    transform(defnVal, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a captor in class scope with implicit type should return a 'var' annotated with '@Captor'") {
    val defnVal = q"private val myCaptor = ArgCaptor[Foo]"

    val expectedDeclVar =
      q"""
      @Captor
      private var myCaptor: Captor[Foo]
      """

    transform(defnVal, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a mock in block scope should return None") {
    val defnVal = q"private val foo = mock[Foo]"
    transform(defnVal, JavaScope.Block) shouldBe None
  }

  test("transform() for a spy in block scope should return None") {
    val defnVal = q"private val foo = spy[Foo]"
    transform(defnVal, JavaScope.Block) shouldBe None
  }

  test("transform() for a captor in block scope should return None") {
    val defnVal = q"private val myCaptor = ArgCaptor[Foo]"
    transform(defnVal, JavaScope.Block) shouldBe None
  }
}
