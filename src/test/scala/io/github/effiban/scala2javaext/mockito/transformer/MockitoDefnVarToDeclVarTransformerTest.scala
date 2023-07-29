package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.entities.JavaScope
import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoDefnVarToDeclVarTransformer.transform

import scala.meta.XtensionQuasiquoteTerm

class MockitoDefnVarToDeclVarTransformerTest extends UnitTestSuite {

  test("transform() for a mock in class scope with explicit type, and 'final', should return a 'var' annotated with '@Mock' and non-'final'") {
    val defnVar = q"private final var myMock: Foo = mock[Foo]()"

    val expectedDeclVar =
      q"""
      @Mock
      private var myMock: Foo
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a mock in class scope with explicit type, and not 'final' should return a 'var' annotated with '@Mock'") {
    val defnVar = q"private var myMock: Foo = mock[Foo]()"

    val expectedDeclVar =
      q"""
      @Mock
      private var myMock: Foo
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a mock in class scope with an implicit type should return a 'var' annotated with '@Mock'") {
    val defnVar = q"private var myMock = mock[Foo]()"

    val expectedDeclVar =
      q"""
      @Mock
      private var myMock: Foo
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a spy in class scope with explicit type and 'final', should return a 'var' annotated with '@Spy' and non-'final'") {
    val defnVar = q"private final var mySpy: Foo = spy[Foo]()"

    val expectedDeclVar =
      q"""
      @Spy
      private var mySpy: Foo
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a spy in class scope with explicit type, and not 'final', should return a 'var' annotated with '@Spy'") {
    val defnVar = q"private var mySpy: Foo = spy[Foo]()"

    val expectedDeclVar =
      q"""
      @Spy
      private var mySpy: Foo
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a spy in class scope with an implicit type should return a 'var' annotated with '@Spy'") {
    val defnVar = q"private var mySpy = spy[Foo]()"

    val expectedDeclVar =
      q"""
      @Spy
      private var mySpy: Foo
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a captor in class scope with explicit type and 'final', should return a 'var' annotated with '@Captor' and not 'final'") {
    val defnVar = q"private final var myCaptor: Captor[Foo] = ArgCaptor.apply[Foo]()"

    val expectedDeclVar =
      q"""
      @JavaCaptor
      private var myCaptor: Captor[Foo]
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a captor in class scope with explicit type and not 'final', should return a 'var' annotated with '@Captor'") {
    val defnVar = q"private var myCaptor: Captor[Foo] = ArgCaptor.apply[Foo]()"

    val expectedDeclVar =
      q"""
      @JavaCaptor
      private var myCaptor: Captor[Foo]
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a captor in class scope with implicit type should return a 'var' annotated with '@Captor'") {
    val defnVar = q"private var myCaptor = ArgCaptor.apply[Foo]()"

    val expectedDeclVar =
      q"""
      @JavaCaptor
      private var myCaptor: Captor[Foo]
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a mock in block scope should return None") {
    val defnVar = q"private var foo = mock[Foo]()"
    transform(defnVar, JavaScope.Block) shouldBe None
  }

  test("transform() for a spy in block scope should return None") {
    val defnVar = q"private var foo = spy[Foo]()"
    transform(defnVar, JavaScope.Block) shouldBe None
  }

  test("transform() for a captor in block scope should return None") {
    val defnVar = q"private var myCaptor = ArgCaptor.apply[Foo]()"
    transform(defnVar, JavaScope.Block) shouldBe None
  }
}
