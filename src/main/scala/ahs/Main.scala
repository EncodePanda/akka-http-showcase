package ahs

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.Http
import ahs.domain._
import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

trait Protocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val statusFormatter = jsonFormat2(SuperHero.apply)
}

object Main extends App with Protocol {
  implicit val system = ActorSystem()
  implicit val actorMaterializer = ActorMaterializer()

  var heros: Map[String, SuperHero] = Map("1" -> PowerPlan.enter("1")("Fish"))

  def insertHero(hero: SuperHero) = {
    heros = heros + (hero.id -> hero)
  }
  val route =

    pathSingleSlash {
      get {
        complete {
          "Hello to SuperHeros forge. Try GET /superhero/"
        }
      }
    } ~
      path("superhero") {
        get {
          complete {
            heros
          }
        } ~
          put {
            entity(as[SuperHero]) { hero =>
              insertHero(hero)
              complete(s"got ${hero.name}")
            }
          }
      }

  Http().bindAndHandle(route, "localhost", 8080)
}
