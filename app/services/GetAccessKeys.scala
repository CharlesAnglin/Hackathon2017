package services

import scala.io.Source

trait GetAccessKeys {

  def getAccessKeys: (String, String) = {
    try {
      val file = Source.fromFile("credentials.csv")
      val line = file.getLines().toStream(1)
      val creds = line.split(",").slice(2,4)
      file.close()
      (creds.head, creds.last)
    } catch {
      case e => throw new Exception("credentials file not found - see readme")
    }
  }

}
