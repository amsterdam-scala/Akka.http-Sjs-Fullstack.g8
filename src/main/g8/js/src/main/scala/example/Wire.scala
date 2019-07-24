package example

import java.nio.ByteBuffer

import boopickle.Default._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js.typedarray.{ArrayBuffer, TypedArrayBuffer}

object Wire extends autowire.Client[ByteBuffer, Pickler, Pickler] {
  override def doCall(req: Request): Future[ByteBuffer] = {
    org.scalajs.dom.ext.Ajax.post(
      url = "/api/".concat(req.path.mkString("/")),
      data = Pickle.intoBytes(req.args),
      responseType = "arraybuffer",
      headers = Map("Content-Type" -> "application/octet-stream")
    ).map(r => TypedArrayBuffer.wrap(r.response.asInstanceOf[ArrayBuffer]))
  }

  override def read[Result: Pickler](p: ByteBuffer): Result = Unpickle[Result].fromBytes(p)

  override def write[Result: Pickler](r: Result): ByteBuffer = Pickle.intoBytes(r)
}