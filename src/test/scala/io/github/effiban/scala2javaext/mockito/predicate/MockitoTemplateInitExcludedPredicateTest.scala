package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2javaext.mockito.testsuites.UnitTestSuite

import scala.meta.{Init, XtensionQuasiquoteInit}

class MockitoTemplateInitExcludedPredicateTest extends UnitTestSuite {

  private val TemplateInitScenarios = Table(
    ("Init", "ExpectedExcluded"),
    (init"ArgumentMatchersSugar", true),
    (init"IdiomaticMockito", true),
    (init"MockitoSugar", true),
    (init"ResetMocksAfterEachTest", true),
    (init"ResetMocksAfterEachAsyncTest", true),
    (init"org.mockito.ArgumentMatchersSugar", true),
    (init"org.mockito.IdiomaticMockito", true),
    (init"org.mockito.MockitoSugar", true),
    (init"org.mockito.integrations.scalatest.ResetMocksAfterEachTest", true),
    (init"org.mockito.integrations.scalatest.ResetMocksAfterEachAsyncTest", true),
    (init"org.mockito.listener.InvocationListener", false),
    (init"InvocationListener", false)
  )

  forAll(TemplateInitScenarios) { case (init: Init, expectedExcluded: Boolean) =>
    test(s"$init should ${if (expectedExcluded) "be" else "not be"} excluded") {
      MockitoTemplateInitExcludedPredicate(init) shouldBe expectedExcluded
    }
  }

}
