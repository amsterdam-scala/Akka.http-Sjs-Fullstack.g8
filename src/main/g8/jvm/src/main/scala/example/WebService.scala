package example

import akka.actor.ActorSystem
import akka.http.scaladsl.model.headers.CacheDirectives.`no-cache`
import akka.http.scaladsl.model.headers.{Referer, `Cache-Control`}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, Uri}
import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.Materializer

import scala.concurrent.ExecutionContext

object WebService extends Directives {
  def route()(implicit s: ActorSystem, m: Materializer, e: ExecutionContext): Route =
    get {
      pathSingleSlash {
        complete {
          HttpEntity(ContentTypes.`text/html(UTF-8)`, string = Page.html)
        }
      } ~
        path("favicon.ico") {
          getFromResource("public/" + "favicon.ico")
        } ~
        (pathPrefix("assets" / Remaining) & respondWithHeader(`Cache-Control`(`no-cache`))) { file =>
          // optionally compresses the response with Gzip or Deflate
          // if the client accepts compressed responses
          getFromResource("public/" + file)
        }
    } ~
      post {
        (path("api" / Segments) & headerValueByName(Referer.name)) { case (segments, referer) =>
          post(AutowireServer.dispatch(segments, Uri(referer).queryString().getOrElse("Unknow Client")))
        }
      }
}