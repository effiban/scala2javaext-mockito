package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.transformers._

trait MockitoTransformers extends ExtendedTransformers {

  override def importerTransformer(): ImporterTransformer = MockitoImporterTransformer

  override def classTransformer(): ClassTransformer = MockitoClassTransformer

  override def defnVarTransformer(): DefnVarTransformer = MockitoDefnVarTransformer

  override def defnVarToDeclVarTransformer(): DefnVarToDeclVarTransformer = MockitoDefnVarToDeclVarTransformer

  override def termApplyTransformer(): TermApplyTransformer = MockitoTermApplyTransformer

  override def typeNameTransformer(): TypeNameTransformer = MockitoTypeNameTransfomer
}
