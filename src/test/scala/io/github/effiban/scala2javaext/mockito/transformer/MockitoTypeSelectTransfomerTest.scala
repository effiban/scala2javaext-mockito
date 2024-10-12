package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoTypeSelectTransfomer.transform
import org.mockito.ArgumentMatchersSugar
import org.mockito.ArgumentMatchersSugar.isA

import scala.meta.{Type, XtensionQuasiquoteType}

class MockitoTypeSelectTransfomerTest extends UnitTestSuite {

  private val TransformableScenarios = Table(
    ("Input", "MaybeExpectedOutput"),
    (t"org.mockito.captor.Captor", t"org.mockito.ArgumentCaptor")
  )

  private val NonTransformableScenarios = Table(
    "Input",
    t"AAA.BBB"
  )

  forAll(TransformableScenarios) {
    (input: Type.Select, expectedOutput: Type) =>
      test(s"$input should be transformed to $expectedOutput") {
        transform(input).value.structure shouldBe expectedOutput.structure
      }
  }

  forAll(NonTransformableScenarios) {
    (input: Type.Select) =>
      test(s"Transform should return None for: $input ") {
        transform(input) shouldBe None
      }
  }
}
