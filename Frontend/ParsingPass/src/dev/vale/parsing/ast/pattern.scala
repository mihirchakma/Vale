package dev.vale.parsing.ast

import dev.vale.lexing.RangeL
import dev.vale.vcurious

//sealed trait IVirtualityP
case class AbstractP(range: RangeL)// extends IVirtualityP
//case class OverrideP(range: RangeP, tyype: ITemplexPT) extends IVirtualityP { override def equals(obj: Any): Boolean = vcurious(); override def hashCode(): Int = vcurious() }

case class PatternPP(
    range: RangeL,
    preBorrow: Option[RangeL],
    capture: Option[INameDeclarationP],

    // If they just have a destructure, this will probably be a ManualSequence(None).
    // If they have just parens, this will probably be a Pack(None).
    // Let's be careful to not allow destructuring packs without Pack here, see MEDP.
    templex: Option[ITemplexPT],

    // Eventually, add an ellipsis: Boolean field here... except we also have
    // to account for the difference between a: T... and a...: T (in one, T is a
    // single type and in the other, T is a pack of types). And we might also want
    // to account for nested parens, like struct Fn:((#Params...), (#Rets...))

    destructure: Option[DestructureP],
    virtuality: Option[AbstractP])

case class DestructureP(
  range: RangeL,
  patterns: Vector[PatternPP]) {

  override def equals(obj: Any): Boolean = vcurious(); override def hashCode(): Int = vcurious()
}

sealed trait INameDeclarationP {
  def range: RangeL
}
case class LocalNameDeclarationP(name: NameP) extends INameDeclarationP { override def equals(obj: Any): Boolean = vcurious(); override def hashCode(): Int = vcurious(); override def range: RangeL = name.range }
case class IgnoredLocalNameDeclarationP(range: RangeL) extends INameDeclarationP { override def equals(obj: Any): Boolean = vcurious(); override def hashCode(): Int = vcurious(); }
case class IterableNameDeclarationP(range: RangeL) extends INameDeclarationP { override def equals(obj: Any): Boolean = vcurious(); override def hashCode(): Int = vcurious() }
case class IteratorNameDeclarationP(range: RangeL) extends INameDeclarationP { override def equals(obj: Any): Boolean = vcurious(); override def hashCode(): Int = vcurious() }
case class IterationOptionNameDeclarationP(range: RangeL) extends INameDeclarationP { override def equals(obj: Any): Boolean = vcurious(); override def hashCode(): Int = vcurious() }
case class ConstructingMemberNameDeclarationP(name: NameP) extends INameDeclarationP { override def equals(obj: Any): Boolean = vcurious(); override def hashCode(): Int = vcurious(); override def range: RangeL = name.range }

object Patterns {
  object capturedWithTypeRune {
    def unapply(arg: PatternPP): Option[(String, String)] = {
      arg match {
        case PatternPP(_, _, Some(LocalNameDeclarationP(NameP(_, name))), Some(NameOrRunePT(NameP(_, kindRune))), None, None) => Some((name.str, kindRune.str))
        case _ => None
      }
    }
  }
  object withType {
    def unapply(arg: PatternPP): Option[ITemplexPT] = {
      arg.templex
    }
  }
  object capture {
    def unapply(arg: PatternPP): Option[String] = {
      arg match {
        case PatternPP(_, _, Some(LocalNameDeclarationP(NameP(_, name))), None, None, None) => Some(name.str)
        case _ => None
      }
    }
  }
  object fromEnv {
    def unapply(arg: PatternPP): Option[String] = {
      arg match {
        case PatternPP(_, _, None | Some(IgnoredLocalNameDeclarationP(_)), Some(NameOrRunePT(NameP(_, kindName))), None, None) => Some(kindName.str)
        case _ => None
      }
    }
  }
  object withDestructure {
    def unapply(arg: PatternPP): Option[Vector[PatternPP]] = {
      arg.destructure match {
        case None => None
        case Some(DestructureP(_, patterns)) => Some(patterns)
      }
    }
  }
  object capturedWithType {
    def unapply(arg: PatternPP): Option[(String, ITemplexPT)] = {
      arg match {
        case PatternPP(_, _, Some(LocalNameDeclarationP(NameP(_, name))), Some(templex), None, None) => Some((name.str, templex))
        case _ => None
      }
    }
  }
}
