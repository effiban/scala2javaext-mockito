package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.entities.JavaScope
import io.github.effiban.scala2java.spi.entities.JavaScope.JavaScope
import io.github.effiban.scala2java.spi.transformers.DefnValTransformer
import io.github.effiban.scala2javaext.mockito.common.MockitoAnnotations.Spy

import scala.meta.{Defn, Term, Type, XtensionQuasiquoteTerm}

object MockitoDefnValTransformer extends DefnValTransformer {

  override def transform(defnVal: Defn.Val, javaScope: JavaScope): Defn.Val = {
    javaScope match {
      case JavaScope.Class => transformMember(defnVal)
      case _ => defnVal
    }
  }

  private def transformMember(defnVal: Defn.Val): Defn.Val = {
    defnVal.rhs match {
      case Term.Apply(Term.ApplyType(q"spy", rhsType :: Nil), initializer :: Nil) => transformSpyMember(defnVal, rhsType, initializer)
      case _ => defnVal
    }
  }

  private def transformSpyMember(member: Defn.Val, rhsType: Type, initializer: Term): Defn.Val = {
    import member._

    val newMods = Spy +: mods
    val resolvedType = decltpe.getOrElse(rhsType)
    Defn.Val(newMods, pats, Some(resolvedType), initializer)
  }
}
