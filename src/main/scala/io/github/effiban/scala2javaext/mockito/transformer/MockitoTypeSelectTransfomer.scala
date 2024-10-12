package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.transformers.TypeSelectTransformer

import scala.meta.{Type, XtensionQuasiquoteType}

object MockitoTypeSelectTransfomer extends TypeSelectTransformer {

  override def transform(typeSelect: Type.Select): Option[Type] = {
    typeSelect match {
      case t"org.mockito.captor.Captor" => Some(t"org.mockito.ArgumentCaptor")
      case _ => None
    }
  }
}
