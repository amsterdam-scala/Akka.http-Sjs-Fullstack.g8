package shared

import java.time.Instant

trait Api {
  def remoteToServerCall(zoneString: String): MyType
}

object Ids {
  val (idPayload, idListGroupHeader, idZoneId, idZTime, idLocTime, idStatus) =
    ("idPayload", "idListGroupHeader", "idZoneId", "idZTime", "idLocTime", "idStatus")
}

case class MyType(referer: String, time: Instant, localtime: String)

object MyType {

/*
  implicit val datePickler: boopickle.Pickler[java.util.Date] =
    transformPickler((t: Long) => new java.util.Date(t))(_.getTime)
*/

  implicit val instantPickler: boopickle.Pickler[Instant] =
    boopickle.DefaultBasic.longPickler.xmap(Instant.ofEpochMilli)(_.toEpochMilli)

}