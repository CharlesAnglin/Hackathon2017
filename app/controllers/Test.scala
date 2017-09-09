package controllers

import play.api.mvc.{Action, Controller}
import services.{Rekog, S3Upload}

class Test extends Controller with S3Upload {

  def test = Action {

//    val a= testtest
//    Ok(a.toString)

//    val tup = uploadFile()
//    Ok(tup._1 + tup._2)

    Ok("hello")
  }

}
