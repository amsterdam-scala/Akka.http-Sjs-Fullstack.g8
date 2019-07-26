package example

import shared.Ids._

object Page {
  val html: String =
    s"""<!DOCTYPE HTML>
       |<html>
       |  <head>
       |    <meta charset="UTF-8">
       |    <meta name="author" content="Cloudius">
       |    <meta name="description" content="">
       |    <meta name="viewport" content="width=device-width initial-scale=1">
       |    <title>Hello Full Scala Stack</title>
       |    <link rel="stylesheet" href="/assets/main.css">
       |    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
       |          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
       |    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
       |    <link rel="manifest" href="/assets/manifest.webmanifest">
       |  </head>
       |
       |  <body>
       |    <div class="jumbotron jumbotron-fluid">
       |      <video autoplay muted loop>
       |        <source src="//storage.googleapis.com/coverr-main/mp4/Up.mp4" type="video/mp4" />
       |      </video>
       |      <div class="container-fluid">
       |        <div class="row">
       |            <div class="col-md-4"></div>
       |              <ul class="list-group my-5">
       |                <li id="${idListGroupHeader}" class="list-group-item active">Waiting for server</li>
       |                <li class="list-group-item">
       |                    <dt>Zone-id of the client, send to server</dt>
       |                    <dd id="${idZoneId}">...</dd>
       |                </li>
       |                <li class="list-group-item">
       |                    <dt>“Zulu” time received from server</dt>
       |                    <dd id="${idZTime}">...</dd>
       |                </li>
       |                <li class="list-group-item">
       |                    <dt>This time converted by server according client's zone</dt>
       |                    <dd id="${idLocTime}">...</dd>
       |                </li>
       |                <li id="${idStatus}" class="list-group-item active">
       |                    Trying to connect with server.
       |                </li>
       |              </ul>
       |        </div>
       |      </div>
       |    </div>
       |
       |    <section class="my-5">
       |      <div class="container row">
       |        <div class="col-md-8 mx-auto">
       |            <h3>Seamless Client-Server integration</h3>
       |            <p>Scala lets you share code between client and server relatively straightforwardly with a shared source module.</p>
       |            <p>That module defines the common <code>Trait</code> (interface if you like), which describes its callable in terms of parameters and return type. The return is a compound, mostly a <code>case class</code>.</p>
       |        </div>
       |        <div class="mx-auto">
       |            <code>.<br>
       |                │&nbsp;&nbsp;&nbsp;build.sbt<br>
       |                │<br>
       |                ├───js<br>
       |                │&nbsp;&nbsp;&nbsp;└───src<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└───main<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; └───scala<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└───example<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Client.scala<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Wire.scala<br>
       |                │<br>
       |                ├───jvm<br>
       |                │&nbsp;&nbsp;&nbsp;└───src<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└───main<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├───public<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;favicon.ico<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;main.css<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├───resources<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;application.conf<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└───scala<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└───example<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;AutowireServer.scala<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MyService.scala<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Page.scala<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;WebServer.scala<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;WebService.scala<br>
       |                │<br>
       |                ├───project<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;build.properties<br>
       |                │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;plugins.sbt<br>
       |                │<br>
       |                └───shared<br>
       |                &nbsp;&nbsp;&nbsp;&nbsp;└───src<br>
       |                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└───main<br>
       |                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└───scala<br>
       |                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└───shared<br>
       |                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;api.scala</code>
       |        </div>
       |      </div>
       |    </section>
       |
       |    <script src="/assets/client-fastopt.js" type="text/javascript"></script>
       |  </body>
       |</html>""".stripMargin

}