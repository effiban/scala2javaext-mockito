package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.entities.JavaScope
import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoDefnVarToDeclVarTransformer.transform

import scala.meta.XtensionQuasiquoteTerm

class MockitoDefnVarToDeclVarTransformerTest extends UnitTestSuite {

  test("transform() for a mock in class scope with explicit type, and 'final', " +
    "and qualified by super[MockitoSugar], " +
    "should return a 'var' annotated with '@org.mockito.Mock' and non-'final'") {

    val defnVar = q"private final var myMock: Foo = super[MockitoSugar].mock[Foo]()"

    val expectedDeclVar =
      q"""
      @org.mockito.Mock
      private var myMock: Foo
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a mock in class scope with explicit type, and 'final', " +
    "and fully-qualified, " +
    "should return a 'var' annotated with '@org.mockito.Mock' and non-'final'") {

    val defnVar = q"private final var myMock: Foo = org.mockito.MockitoSugar.mock[Foo]()"

    val expectedDeclVar =
      q"""
      @org.mockito.Mock
      private var myMock: Foo
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a mock in class scope with explicit type, and not 'final', " +
    "and qualified by super[MockitoSugar], " +
    "should return a 'var' annotated with '@org.mockito.Mock'") {

    val defnVar = q"private var myMock: Foo = super[MockitoSugar].mock[Foo]()"

    val expectedDeclVar =
      q"""
      @org.mockito.Mock
      private var myMock: Foo
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a mock in class scope with an implicit type," +
    "and qualified by super[MockitoSugar], " +
    " should return a 'var' annotated with '@org.mockito.Mock'") {

    val defnVar = q"private var myMock = super[MockitoSugar].mock[Foo]()"

    val expectedDeclVar =
      q"""
      @org.mockito.Mock
      private var myMock: Foo
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a mock in class scope with an implicit type," +
    "and fully-qualified, " +
    " should return a 'var' annotated with '@org.mockito.Mock'") {

    val defnVar = q"private var myMock = org.mockito.MockitoSugar.mock[Foo]()"

    val expectedDeclVar =
      q"""
      @org.mockito.Mock
      private var myMock: Foo
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a spy in class scope should return None") {
    val defnVar = q"private var foo = org.mockito.MockitoSugar.spy[Foo](new Foo())"
    transform(defnVar, JavaScope.Class) shouldBe None
  }

  test("transform() for a captor in class scope with explicit type and 'final', " +
    "should return a 'var' annotated with '@org.mockito.Captor' and not 'final'") {

    val defnVar = q"private final var myCaptor: org.mockito.captor.Captor[Foo] = org.mockito.captor.ArgCaptor.apply[Foo]()"

    val expectedDeclVar =
      q"""
      @org.mockito.Captor
      private var myCaptor: org.mockito.captor.Captor[Foo]
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a captor in class scope with explicit type and not 'final', " +
    "should return a 'var' annotated with '@org.mockito.Captor'") {

    val defnVar = q"private var myCaptor: org.mockito.captor.Captor[Foo] = org.mockito.captor.ArgCaptor.apply[Foo]()"

    val expectedDeclVar =
      q"""
      @org.mockito.Captor
      private var myCaptor: org.mockito.captor.Captor[Foo]
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a captor in class scope with implicit type," +
    " should return a 'var' annotated with '@org.mockito.Captor'") {

    val defnVar = q"private var myCaptor = org.mockito.captor.ArgCaptor.apply[Foo]()"

    val expectedDeclVar =
      q"""
      @org.mockito.Captor
      private var myCaptor: org.mockito.captor.Captor[Foo]
      """

    transform(defnVar, JavaScope.Class).value.structure shouldBe expectedDeclVar.structure
  }

  test("transform() for a mock in block scope, qualified by super[MockitoSugar], should return None") {
    val defnVar = q"private var foo = super[MockitoSugar].mock[Foo]()"
    transform(defnVar, JavaScope.Block) shouldBe None
  }

  test("transform() for a mock in block scope, fully-qualified, should return None") {
    val defnVar = q"private var foo = org.mockito.MockitoSugar.mock[Foo]()"
    transform(defnVar, JavaScope.Block) shouldBe None
  }

  test("transform() for a spy in block scope should return None") {
    val defnVar = q"private var foo = org.mockito.MockitoSugar.spy[Foo](new Foo())"
    transform(defnVar, JavaScope.Block) shouldBe None
  }

  test("transform() for a captor in block scope should return None") {
    val defnVar = q"private var myCaptor = org.mockito.captor.ArgCaptor.apply[Foo]()"
    transform(defnVar, JavaScope.Block) shouldBe None
  }
}
