package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoTermApplyTransformer.transform

import scala.meta.{Term, XtensionQuasiquoteTerm}

class MockitoTermApplyTransformerTest extends UnitTestSuite {

  private val Scenarios = Table(
    ("Input", "ExpectedOutput"),
    (q"ArgCaptor(classOf[Foo])", q"ArgCaptor.apply(classOf[Foo])"),
    (q"eqTo(33)", q"eq(33)"),
    (q"spy(4)", q"spy(4)"),
    (q"spy[Int](4)", q"spy(4)"),
    (q"blabla(4)", q"blabla(4)")
  )

  forAll(Scenarios) {
    (input: Term.Apply, expectedOutput: Term.Apply) =>
      test(s"$input should be transformed to $expectedOutput") {
        transform(input).structure shouldBe expectedOutput.structure
      }
  }
}
