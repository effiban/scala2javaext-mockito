package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2java.spi.typeinferrers.{ApplyDeclDefInferrer, ExtendedTypeInferrers}

trait MockitoTypeInferrers extends ExtendedTypeInferrers {

  override def applyDeclDefInferrer(): ApplyDeclDefInferrer = MockitoApplyDeclDefInferrer
}
