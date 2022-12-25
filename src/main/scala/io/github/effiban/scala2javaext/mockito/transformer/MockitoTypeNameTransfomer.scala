package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.transformers.TypeNameTransformer

import scala.meta.Type
import scala.meta.XtensionQuasiquoteType

object MockitoTypeNameTransfomer extends TypeNameTransformer {

  override def transform(typeName: Type.Name): Type.Name = {
    typeName match {
      case t"Captor" => t"ArgumentCaptor"
      case t"JavaCaptor" => t"Captor"
      case aTypeName => aTypeName
    }
  }
}
