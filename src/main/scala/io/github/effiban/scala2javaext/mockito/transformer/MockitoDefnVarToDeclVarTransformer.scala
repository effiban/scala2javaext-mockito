package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.entities.JavaScope
import io.github.effiban.scala2java.spi.entities.JavaScope.JavaScope
import io.github.effiban.scala2java.spi.transformers.DefnVarToDeclVarTransformer
import io.github.effiban.scala2javaext.mockito.common.MockitoAnnotations.{JavaCaptor, Mock, Spy}

import scala.meta.{Decl, Defn, Mod, Term, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

object MockitoDefnVarToDeclVarTransformer extends DefnVarToDeclVarTransformer {

  override def transform(defnVar: Defn.Var, javaScope: JavaScope): Option[Decl.Var] = {
    javaScope match {
      case JavaScope.Class => transformMember(defnVar)
      case _ => None
    }
  }

  private def transformMember(defnVar: Defn.Var): Option[Decl.Var] = {
    defnVar.rhs match {
      case Some(Term.Apply(Term.ApplyType(q"mock", _), Nil)) => transformMockOrSpyMember(defnVar, Mock)
      case Some(Term.Apply(Term.ApplyType(q"spy", _), Nil)) => transformMockOrSpyMember(defnVar, Spy)
      case Some(Term.Apply(Term.ApplyType(q"ArgCaptor.apply", _), Nil)) => transformCaptorMember(defnVar)
      case _ => None
    }
  }

  private def transformMockOrSpyMember(defnVar: Defn.Var, annot: Mod.Annot): Option[Decl.Var] = {
    import defnVar._

    val newMods = (annot +: mods).filterNot(_.isInstanceOf[Mod.Final])
    (decltpe, rhs) match {
      case (Some(tpe), _) => Some(Decl.Var(newMods, pats, tpe))
      case (None, Some(Term.Apply(Term.ApplyType(_, tpe :: Nil), Nil))) => Some(Decl.Var(newMods, pats, tpe))
      case _ => None
    }
  }

  private def transformCaptorMember(defnVar: Defn.Var): Option[Decl.Var] = {
    import defnVar._

    val newMods = (JavaCaptor +: mods).filterNot(_.isInstanceOf[Mod.Final])
    (decltpe, rhs) match {
      case (Some(tpe), _) => Some(Decl.Var(newMods, pats, tpe))
      case (None, Some(Term.Apply(Term.ApplyType(_, tpe :: Nil), Nil))) => Some(Decl.Var(newMods, pats, t"Captor[$tpe]"))
      case _ => None
    }
  }
}
