package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.transformers._

trait MockitoTransformers extends ExtendedTransformers {

  override def classTransformer(): ClassTransformer = MockitoClassTransformer

  override def defnVarTransformer(): DefnVarTransformer = MockitoDefnVarTransformer

  override def defnVarToDeclVarTransformer(): DefnVarToDeclVarTransformer = MockitoDefnVarToDeclVarTransformer

  override def qualifiedTermApplyTransformer(): QualifiedTermApplyTransformer = MockitoQualifiedTermApplyTransformer

  override def typeSelectTransformer(): TypeSelectTransformer = MockitoTypeSelectTransfomer
}
