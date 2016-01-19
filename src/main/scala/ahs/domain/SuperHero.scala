package ahs.domain

sealed trait Power
case object Flying extends Power
case object ShootinFire extends Power


case class SuperHero(name: String) 


object PowerPlan {
  def enter(thinkingAbout: String) = SuperHero(s"${thinkingAbout}er Man")
}
