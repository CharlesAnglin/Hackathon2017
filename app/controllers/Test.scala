package controllers

import play.api.mvc.{Action, Controller}
import services.UploadS3

class Test extends UploadS3 with Controller {

  def test = Action {
    uploadFile("charlie2.png")
    Ok(s"$accessKey\n$secretKey")
  }

}
