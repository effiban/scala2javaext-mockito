package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoTermSelectTransformer.transform

import scala.meta.{Term, XtensionQuasiquoteTerm}

class MockitoTermSelectTransformerTest extends UnitTestSuite {

  private val Scenarios = Table(
    ("Input", "ExpectedOutput"),
    (q"ArgCaptor.apply", q"ArgumentCaptor.forClass"),
    (q"aaa.bbb", q"aaa.bbb")
  )

  forAll(Scenarios) {
    (input: Term.Select, expectedOutput: Term.Select) =>
      test(s"$input should be transformed to $expectedOutput") {
        transform(input).structure shouldBe expectedOutput.structure
      }
  }
}
