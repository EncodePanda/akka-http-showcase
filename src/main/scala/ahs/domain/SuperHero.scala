package ahs.domain

sealed trait Power
case object Flying extends Power
case object ShootinFire extends Power

case class SuperHero(id: Long, name: String) 

object PowerPlan {
  def enter(id: Long)(thinkingAbout: String) = SuperHero(id, s"${thinkingAbout}er Man")
}
