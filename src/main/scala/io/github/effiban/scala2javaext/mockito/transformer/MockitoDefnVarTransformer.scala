package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.entities.JavaScope
import io.github.effiban.scala2java.spi.entities.JavaScope.JavaScope
import io.github.effiban.scala2java.spi.transformers.DefnVarTransformer
import io.github.effiban.scala2javaext.mockito.common.MockitoJavaAnnotations.Spy

import scala.meta.{Defn, Name, Term, Type, XtensionQuasiquoteTerm}

object MockitoDefnVarTransformer extends DefnVarTransformer {

  override def transform(defnVar: Defn.Var, javaScope: JavaScope): Defn.Var = {
    javaScope match {
      case JavaScope.Class => transformMember(defnVar)
      case _ => defnVar
    }
  }

  private def transformMember(defnVar: Defn.Var): Defn.Var = {
    defnVar.rhs match {
      case Some(Term.Apply(
        Term.ApplyType(Term.Select(Term.Super(_, Name.Indeterminate("MockitoSugar")), q"spy"), rhsType :: Nil),
      initializer :: Nil)) =>
        transformSpyMember(defnVar, rhsType, initializer)
      case Some(Term.Apply(Term.ApplyType(q"org.mockito.MockitoSugar.spy", rhsType :: Nil),initializer :: Nil)) =>
        transformSpyMember(defnVar, rhsType, initializer)
      case _ => defnVar
    }
  }

  private def transformSpyMember(member: Defn.Var, rhsType: Type, initializer: Term): Defn.Var = {
    import member._

    val newMods = Spy +: mods
    val resolvedType = decltpe.getOrElse(rhsType)
    Defn.Var(newMods, pats, Some(resolvedType), Some(initializer))
  }
}
