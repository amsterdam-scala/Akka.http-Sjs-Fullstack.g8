package example

import java.time.temporal.ChronoUnit.MILLIS
import java.time.{Instant, LocalDateTime, ZoneId}

import example.MyService.once
import shared.{Api, MyType}

class MyService(referer: String) extends Api {

  def remoteToServerCall(zoneIdStr: String): MyType = {
    val instant = Instant.now().truncatedTo(MILLIS)
    if (once.isEmpty || once.get != zoneIdStr) {
      once = Option(zoneIdStr)
      println(s"Received zoneIdStr of client: $zoneIdStr")
    }

    def ldt = LocalDateTime.ofInstant(instant, ZoneId.of(zoneIdStr))

    MyType(referer, instant, ldt.atZone(ZoneId.of(zoneIdStr)).toString)
  }
}

object MyService {
  var once: Option[String] = None

}