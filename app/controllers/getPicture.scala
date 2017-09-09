package controllers

import java.io._
import java.util.Base64

import play.api.mvc._
import services.{GetAccessKeys, S3Upload, UploadS3}

import scala.io.Source

class getPicture extends Controller with S3Upload {

  def getPicture() = Action {
    //TODO: html hardcoded to go to localhost:9000
    Ok(views.html.getPicture())
  }

  def postPicture() = Action(parse.temporaryFile) { request =>
    val tempFile = "tmp/tempFileFile.txt"
    request.body.moveTo(new File(tempFile), true)

    val fileName = saveFile(tempFile)
    uploadFile(s"$fileName.png")

    Ok("File uploaded to S3" + "\n" + s"fileName: $fileName")
  }

  private def saveFile(tempFile: String): String = {
    val file = Source.fromFile(tempFile)
    val lines = file.getLines.toStream
    val fileName = lines(3)
    val img = lines(7)
      .replace("data:image/png;base64,", "")
      .replace(" ", "+")

    val decodedImg = Base64.getDecoder().decode(img)

    val bos = new BufferedOutputStream(new FileOutputStream(s"tmp/$fileName.png"))
    bos.write(decodedImg)
    file.close()
    bos.close()
    fileName
  }

}
