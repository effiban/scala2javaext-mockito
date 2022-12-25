package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite
import io.github.effiban.scala2javaext.mockito.transformer.MockitoTypeNameTransfomer.transform

import scala.meta.{Type, XtensionQuasiquoteType}

class MockitoTypeNameTransfomerTest extends UnitTestSuite {

  private val Scenarios = Table(
    ("Input", "ExpectedOutput"),
    (t"Captor", t"ArgumentCaptor"),
    (t"JavaCaptor", t"Captor"),
    (t"AAA", t"AAA")
  )

  forAll(Scenarios) {
    (input: Type.Name, expectedOutput: Type.Name) =>
      test(s"$input should be transformed to $expectedOutput") {
        transform(input).structure shouldBe expectedOutput.structure
      }
  }
}
