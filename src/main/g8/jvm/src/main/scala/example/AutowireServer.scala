package example

import java.nio.ByteBuffer

import akka.http.scaladsl.server.Directives.{as, complete, entity, onSuccess}
import akka.http.scaladsl.server.{RequestContext, RouteResult}
import akka.util.ByteString
import boopickle.Default._

import scala.concurrent.{ExecutionContext, Future}
import shared.MyType.instantPickler

object AutowireServer extends autowire.Server[ByteBuffer, Pickler, Pickler] {
  override def read[R: Pickler](p: ByteBuffer): R = Unpickle[R].fromBytes(p)

  override def write[R: Pickler](r: R): ByteBuffer = Pickle.intoBytes(r)

  def dispatch(segments: List[String], referer: String)
              (implicit ec: ExecutionContext): RequestContext => Future[RouteResult] =
    entity(as[ByteString]) { entity =>
      val service = new MyService(referer)

      def body = Unpickle[Map[String, ByteBuffer]].fromBytes(entity.asByteBuffer)

      def request: Future[ByteBuffer] = AutowireServer.route[shared.Api](service)(autowire.Core.Request(segments, body))

      onSuccess(request)(buffer => complete(ByteString(buffer)))
    }
}