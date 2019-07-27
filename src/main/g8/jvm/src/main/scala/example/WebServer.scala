package example

import java.awt.Desktop
import java.net.URI

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object WebServer {
  def main(args: Array[String]) {
    implicit val system = ActorSystem("server-system")
    implicit val materializer = ActorMaterializer()

    val config = ConfigFactory.load().getConfig("akka.http.server")
    val (host, port) = (config.getString("host"), config.getInt("port"))

    def handle = Http().bindAndHandle(WebService.route(), host, port)

    handle.onComplete { case Success(value) =>
      def interface = if (host == "0.0.0.0") "127.0.0.1" else host

      val clientUri = new URI("http", null, interface, port, null, "Server invoked client session", null)

      println(s"Server listening at ${clientUri.getRawAuthority}")

/*
      if (Desktop.isDesktopSupported && Desktop.getDesktop.isSupported(Desktop.Action.BROWSE))
        Desktop.getDesktop.browse(clientUri)
      else println(s"Automatic start of Web browser not possible.\nWeb browser must be started manually.")
*/

    case Failure(exception) =>
    }
  }
}