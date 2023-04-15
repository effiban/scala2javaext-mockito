package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.transformers._

trait MockitoTransformers extends ExtendedTransformers {

  override def importerTransformer(): ImporterTransformer = MockitoImporterTransformer

  override def classTransformer(): ClassTransformer = MockitoClassTransformer

  override def defnValTransformer(): DefnValTransformer = MockitoDefnValTransformer

  override def defnValToDeclVarTransformer(): DefnValToDeclVarTransformer = MockitoDefnValToDeclVarTransformer

  override def termApplyTransformer(): TermApplyTransformer = MockitoTermApplyTransformer

  override def typeNameTransformer(): TypeNameTransformer = MockitoTypeNameTransfomer
}
