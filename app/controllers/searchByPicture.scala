package controllers

import java.io.File
import play.api.mvc.{Action, Controller}
import services.{LocalSaveFile, Rekog}

class searchByPicture extends Controller with Rekog with LocalSaveFile {

  def getPicture() = Action {
    //TODO: html hardcoded to go to localhost:9000
    Ok(views.html.getPicture2())
  }

  def searchByPicture = Action(parse.temporaryFile) { request =>
    val tempFile = "tmp/tempFileFile.txt"
    request.body.moveTo(new File(tempFile), true)

    val (fileName, name) = saveFile(tempFile)
    val faces = searchByFace(fileName).toArray().toList.map(_.toString).mkString("\n\n")
    Ok(faces)
  }
}
