package ahs.domain

sealed trait Power
case object Flying extends Power
case object ShootinFire extends Power


case class SuperHero(id: String, name: String) 


object PowerPlan {
  def enter(id: String)(thinkingAbout: String) = SuperHero(id, s"${thinkingAbout}er Man")
}
