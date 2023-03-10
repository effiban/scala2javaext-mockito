package io.github.effiban.scala2javaext.mockito.typeinferrers

import io.github.effiban.scala2java.spi.typeinferrers.{ApplyTypeInferrer, ApplyTypeTypeInferrer, ExtendedTypeInferrers, NameTypeInferrer}

trait MockitoTypeInferrers extends ExtendedTypeInferrers {

  override def applyTypeInferrer(): ApplyTypeInferrer = MockitoApplyTypeInferrer

  override def applyTypeTypeInferrer(): ApplyTypeTypeInferrer = MockitoApplyTypeTypeInferrer

  override def nameTypeInferrer(): NameTypeInferrer = MockitoNameTypeInferrer
}
