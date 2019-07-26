package todo

import org.scalajs.dom.experimental.Fetch._
import org.scalajs.dom.experimental.serviceworkers.ServiceWorkerGlobalScope._
import org.scalajs.dom.experimental.serviceworkers.{ExtendableEvent, FetchEvent}
import org.scalajs.dom.experimental.{Request, RequestCache, RequestInfo, RequestMode, Response}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.util.{Failure, Success}

object ServiceWorker {

  def main(args: Array[String]): Unit = {

    println("main: ServiceWorker installing...")
  }

  def toCache: Future[Unit] = {
    Future.unit
  }

/*
  def fromCache(request: Request): Future[Response] = {
  }
*/

  def invalidateCache(): Unit =  {
  }
}