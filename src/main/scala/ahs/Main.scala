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

  var heros: Map[Long, SuperHero] = Map(1L -> PowerPlan.enter(1)("Fish"))

  def insertHero(hero: SuperHero) = {

  }
  val route =

    pathSingleSlash {
      get {
        complete {
          "Hello to SuperHeros forge. Try GET /superhero/"
        }
      }
    } ~
      pathPrefix("superhero") {
        pathEndOrSingleSlash {
          get {
            complete {
              heros.values.toList
            }
          } ~
            put {
              entity(as[SuperHero]) { hero =>
                heros = heros + (hero.id -> hero)                
                complete(s"got ${hero.name}")
              }
            }
        } ~
        path(LongNumber) { id =>
          get {
            complete(heros(id))
          } ~
          delete {
            heros = heros - id
            complete("deleted hero id = ${id}")
          }
        }
      }

  Http().bindAndHandle(route, "localhost", 8080)
}
