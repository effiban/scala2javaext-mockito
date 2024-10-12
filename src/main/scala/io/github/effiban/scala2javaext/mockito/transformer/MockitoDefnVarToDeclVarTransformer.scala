package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.entities.JavaScope
import io.github.effiban.scala2java.spi.entities.JavaScope.JavaScope
import io.github.effiban.scala2java.spi.transformers.DefnVarToDeclVarTransformer
import io.github.effiban.scala2javaext.mockito.common.MockitoJavaAnnotations.{Captor, Mock}

import scala.meta.{Decl, Defn, Mod, Name, Term, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

object MockitoDefnVarToDeclVarTransformer extends DefnVarToDeclVarTransformer {

  override def transform(defnVar: Defn.Var, javaScope: JavaScope): Option[Decl.Var] = {
    javaScope match {
      case JavaScope.Class => transformMember(defnVar)
      case _ => None
    }
  }

  private def transformMember(defnVar: Defn.Var): Option[Decl.Var] = {
    defnVar.rhs match {
      case Some(Term.Apply(Term.ApplyType(Term.Select(Term.Super(_, Name.Indeterminate("MockitoSugar")), q"mock"), _), _))
        => transformMockMember(defnVar)
      case Some(Term.Apply(Term.ApplyType(q"org.mockito.MockitoSugar.mock", _), Nil)) => transformMockMember(defnVar)
      case Some(Term.Apply(Term.ApplyType(q"org.mockito.captor.ArgCaptor.apply", _), Nil)) => transformCaptorMember(defnVar)
      case _ => None
    }
  }

  private def transformMockMember(defnVar: Defn.Var): Option[Decl.Var] = {
    import defnVar._

    val newMods = (Mock +: mods).filterNot(_.isInstanceOf[Mod.Final])
    (decltpe, rhs) match {
      case (Some(tpe), _) => Some(Decl.Var(newMods, pats, tpe))
      case (None, Some(Term.Apply(Term.ApplyType(_, tpe :: Nil), Nil))) => Some(Decl.Var(newMods, pats, tpe))
      case _ => None
    }
  }

  private def transformCaptorMember(defnVar: Defn.Var): Option[Decl.Var] = {
    import defnVar._

    val newMods = (Captor +: mods).filterNot(_.isInstanceOf[Mod.Final])
    (decltpe, rhs) match {
      case (Some(tpe), _) => Some(Decl.Var(newMods, pats, tpe))
      case (None, Some(Term.Apply(Term.ApplyType(_, tpe :: Nil), Nil))) =>
        Some(Decl.Var(newMods, pats, t"org.mockito.captor.Captor[$tpe]"))
      case _ => None
    }
  }
}
