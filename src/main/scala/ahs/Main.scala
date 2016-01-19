package ahs

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.Http
import ahs.domain._
import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport


trait Protocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val statusFormatter = jsonFormat1(SuperHero.apply)
}

object Main extends App with Protocol {
  implicit val system = ActorSystem()
  implicit val actorMaterializer = ActorMaterializer()

  var heros: List[SuperHero] = List(PowerPlan.enter("Fish"))

  val route =

    pathSingleSlash {
      get {
        complete {
          "Hello to SuperHeros forge. Try GET /superhero/"
        }
      }
    } ~
  path("superhero") {
    complete {
      heros
    }
  }

  Http().bindAndHandle(route, "localhost", 8080)
}
