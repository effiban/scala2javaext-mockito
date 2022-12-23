package io.github.effiban.scala2javaext.mockito.transformer

import io.github.effiban.scala2java.spi.entities.JavaScope
import io.github.effiban.scala2java.spi.entities.JavaScope.JavaScope
import io.github.effiban.scala2java.spi.transformers.DefnValToDeclVarTransformer
import io.github.effiban.scala2javaext.mockito.common.MockitoAnnotations.{Captor, Mock, Spy}

import scala.meta.{Decl, Defn, Mod, Term, XtensionQuasiquoteTerm, XtensionQuasiquoteType}

object MockitoDefnValToDeclVarTransformer extends DefnValToDeclVarTransformer {

  override def transform(defnVal: Defn.Val, javaScope: JavaScope): Option[Decl.Var] = {
    javaScope match {
      case JavaScope.Class => transformMember(defnVal)
      case _ => None
    }
  }

  private def transformMember(defnVal: Defn.Val): Option[Decl.Var] = {
    defnVal.rhs match {
      case Term.ApplyType(q"mock", _) => transformMockOrSpyMember(defnVal, Mock)
      case Term.ApplyType(q"spy", _) => transformMockOrSpyMember(defnVal, Spy)
      case Term.ApplyType(q"ArgCaptor", _) => transformCaptorMember(defnVal)
      case _ => None
    }
  }

  private def transformMockOrSpyMember(defnVal: Defn.Val, annot: Mod.Annot): Option[Decl.Var] = {
    import defnVal._

    val newMods = annot +: mods
    (decltpe, rhs) match {
      case (Some(tpe), _) => Some(Decl.Var(newMods, pats, tpe))
      case (None, Term.ApplyType(_, tpe :: Nil)) => Some(Decl.Var(newMods, pats, tpe))
      case _ => None
    }
  }

  private def transformCaptorMember(defnVal: Defn.Val): Option[Decl.Var] = {
    import defnVal._

    val newMods = Captor +: mods
    (decltpe, rhs) match {
      case (Some(tpe), _) => Some(Decl.Var(newMods, pats, tpe))
      case (None, Term.ApplyType(_, tpe :: Nil)) => Some(Decl.Var(newMods, pats, t"Captor[$tpe]"))
      case _ => None
    }
  }
}
