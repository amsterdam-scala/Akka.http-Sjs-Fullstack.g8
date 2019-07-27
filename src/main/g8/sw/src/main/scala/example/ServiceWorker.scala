package example

import org.scalajs.dom.experimental.Fetch.fetch
import org.scalajs.dom.experimental.serviceworkers.ServiceWorkerGlobalScope.self
import org.scalajs.dom.experimental.serviceworkers.{ExtendableEvent, FetchEvent}
import org.scalajs.dom.experimental.{Request, RequestCache, RequestInfo, RequestMode, Response}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.util.{Failure, Success}

object ServiceWorker {
  val todoCache = "todo-cache"
  def todoAssets: js.Array[RequestInfo] = List[RequestInfo](
    /*"/",*/
    "index.html",
    "style.css",
    // "w3c.4.13.css",
    "favicon.ico",
   //"logo.png",
/*     "logo-96.png",
    "logo-128.png",
    "logo-170.png",*/
    "android-icon-192x192.png",
    /*"logo-256.png",
    "logo-341.png",
    "logo-384.png",
    "logo-512.png",*/
    "client-fastopt.js" /*,
    "sharedjs-opt.js"*/
  ).toJSArray

  def main(args: Array[String]): Unit = {
    self.addEventListener("install", (event: ExtendableEvent) => {
      println(s"pwa install: service worker installed > ${event.toString}")
      event.waitUntil(toCache.toJSPromise)
    })

    self.addEventListener("activate", (event: ExtendableEvent) => {
      println(s"pwa activate: service worker activated > ${event.toString}")
      invalidateCache()
      self.clients.claim()
    })

    self.addEventListener("fetch", (event: FetchEvent) => {
      if (event.request.cache == RequestCache.`only-if-cached`
        && event.request.mode != RequestMode.`same-origin`) {
        println(s"pwa fetch: Bug [823392] cache === only-if-cached && mode !== same-orgin' > ${event.request.url}")
      } else {
        fromCache(event.request).onComplete {
          case Success(response) =>
            println(s"pwa fetch: in cache > ${event.request.url}")
            response
          case Failure(error) =>
            println(s"pwa fetch: not in cache, calling server... > ${event.request.url} > ${error.printStackTrace()}")
            fetch(event.request)
              .toFuture
              .onComplete {
                case Success(response) => response
                case Failure(finalError) => println(s"pwa fetch: final fetch failed > ${finalError.printStackTrace()}")
              }
        }
      }
    })

    println("main: ServiceWorker installing...")
  }

  private def fromCache(request: Request): Future[Response] = {
    self.caches.`match`(request)
      .toFuture
      .asInstanceOf[Future[Response]]
      .map { response: Response =>
        println(s"pwa fromCache: matched request > ${request.url}")
        response
      }
  }

  private def invalidateCache(): Unit = {
    self.caches.delete(todoCache)
      .toFuture
      .map { invalidatedCache =>
        if (invalidatedCache) {
          println(s"pwa invalidateCache: cache invalidated!', $invalidatedCache")
          toCache
        }
      }
    ()
  }

  private def toCache: Future[Unit] = {
    self.caches.open(todoCache)
      .toFuture
      .onComplete {
        case Success(cache) =>
          println("toCache: caching assets...")
          cache.addAll(todoAssets).toFuture
        case Failure(error) =>
          println(s"pwa toCache: failed > ${error.printStackTrace()}")
      }
    Future.unit
  }
}