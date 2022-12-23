package io.github.effiban.scala2javaext.mockito.predicate

import io.github.effiban.scala2java.spi.predicates.TemplateInitExcludedPredicate

import scala.meta.{Init, XtensionQuasiquoteType}


object MockitoTemplateInitExcludedPredicate extends TemplateInitExcludedPredicate {

  override def apply(init: Init): Boolean = init.tpe match {
    case t"ArgumentMatchersSugar" |
         t"IdiomaticMockito" |
         t"MockitoSugar" |
         t"ResetMocksAfterEachAsyncTest" |
         t"ResetMocksAfterEachTest" |
         t"org.mockito.ArgumentMatchersSugar" |
         t"org.mockito.IdiomaticMockito" |
         t"org.mockito.MockitoSugar" |
         t"org.mockito.integrations.scalatest.ResetMocksAfterEachAsyncTest" |
         t"org.mockito.integrations.scalatest.ResetMocksAfterEachTest" => true
    case _ => false
  }
}
