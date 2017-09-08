package services

import java.io.File

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client

class UploadS3 extends GetAccessKeys {

  val bucketName = "anglin"

  lazy val (accessKey,secretKey) = getAccessKeys

  val yourAWSCredentials = new BasicAWSCredentials(accessKey, secretKey)
  val amazonS3Client = new AmazonS3Client(yourAWSCredentials)

  def uploadFile(fileName: String) = {
    val fileToUpload = new File(s"tmp/$fileName")

    //What happens if there are duplicate file names?
    amazonS3Client.putObject(bucketName, fileName, fileToUpload)
  }

}
