package example

import java.time.ZoneId
import java.time.format.DateTimeFormatter

import autowire._
import boopickle.Default._
import org.scalajs.dom
import shared.MyType.instantPickler
import scalatags.JsDom.all._
import shared.Ids

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.util.{Failure, Success}

object Client {

  def main(args: Array[String]): Unit = {
    lazy val zoneId = ZoneId.systemDefault().getId

    println(s"Trying to get time from server …")
    el[dom.html.Div](shared.Ids.idZoneId).textContent = zoneId.toString

    def run(): Unit = {

      Wire[shared.Api].remoteToServerCall(zoneId).call().onComplete {
        //            |                  |             |
        //            |                  |             The T is pickled and wrapped in a Future[T]
        //            |                  The arguments to that method are pickled automatically
        //            Call a method on the `Api` trait

        case Success(myType) =>
          Map[String, String](
            Ids.idListGroupHeader -> (if (myType.referer.isEmpty) "Unknown client" else myType.referer),
            Ids.idZoneId -> zoneId,
            Ids.idZTime -> DateTimeFormatter.ISO_INSTANT.format(myType.time),
            Ids.idLocTime -> myType.localtime,
            Ids.idStatus -> "Connected to server.").foreach { case (item, str) =>
            el[dom.html.DD](item).textContent = str
          }

        case Failure(throwable) =>

          // Strikethrough time elements
          Seq(Ids.idZTime, Ids.idLocTime).foreach { id =>
            val element = el[dom.html.DD](id)
            val str = element.textContent
            element.innerHTML = ""
            element.appendChild(del(str).render)
          }

          el[dom.html.DD](Ids.idStatus).textContent = "Lost connection to server."
        // println("Failed call: ".concat(throwable.toString))
      }
    }

    js.timers.setInterval(500) {
      run()
    }
  }

  def el[T <: dom.raw.HTMLElement](id: String) = dom.document.getElementById(id).asInstanceOf[T]
}