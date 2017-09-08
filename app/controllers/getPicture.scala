package controllers

import java.io._
import java.util.Base64

import play.api.mvc._
import services.GetAccessKeys

import scala.io.Source

class getPicture extends Controller {

  def getPicture() = Action {
    //html hardcoded to go to localhost:9000
    Ok(views.html.getPicture())
  }

  def postPicture() = Action(parse.temporaryFile) { request =>
    val tempFile = "tmp/tempFileFile.txt"
    request.body.moveTo(new File(tempFile), true)

    val file = Source.fromFile(tempFile)
    val lines = file.getLines.toStream
    file.close()
    val fileName = lines(3)
    val img = lines(7)
      .replace("data:image/png;base64,", "")
      .replace(" ", "+")

    val decodedImg = Base64.getDecoder().decode(img)

    val bos = new BufferedOutputStream(new FileOutputStream(s"tmp/$fileName.png"))
    bos.write(decodedImg)
    bos.close()

    Ok("File uploaded" + "\n" + s"fileName: $fileName")
  }

}
