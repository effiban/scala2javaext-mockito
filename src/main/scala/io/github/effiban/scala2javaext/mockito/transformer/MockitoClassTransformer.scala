package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.transformers.ClassTransformer

import scala.meta.{Defn, XtensionQuasiquoteMod}

object MockitoClassTransformer extends ClassTransformer {

  override def transform(defnClass: Defn.Class): Defn.Class = {
    defnClass.copy(mods = defnClass.mods :+
      mod"@org.junit.jupiter.api.extension.ExtendWith(classOf[org.mockito.junit.jupiter.MockitoExtension])")
  }
}
