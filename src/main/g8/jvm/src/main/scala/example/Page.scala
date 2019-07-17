package example

import shared.Ids._

object Page {
  val html: String =
    s"""<!DOCTYPE HTML>
       |<html>
       |<head>
       |    <meta charset="UTF-8">
       |    <meta name="author" content="Cloudius">
       |    <meta name="description" content="">
       |    <meta name="viewport" content="width=device-width initial-scale=1">
       |    <title>Hello Full Scala Stack</title>
       |    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
       |    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
       |</head>
       |
       |<body>
       |<div class="container-fluid">
       |    <div class="row">
       |        <div class="col-md-4"></div>
       |        <dl>
       |            <ul id="${idPayload}" class="list-group">
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
       |                Trying to connect with server.
       |                </li>
       |            </div>
       |        </dl>
       |    </div>
       |</div>
       |<script src="/assets/client-fastopt.js" type="text/javascript"></script>
       |</body>
       |</html>""".stripMargin

}