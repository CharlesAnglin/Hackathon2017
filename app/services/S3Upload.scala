package services

import java.io.File
import utils.Constants._
import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder

trait S3Upload extends GetAccessKeys {

  lazy val (accessKey, secretKey) = getAccessKeys

  val yourAWSCredentials = new BasicAWSCredentials(accessKey, secretKey)

  //N.B. region MUST be EU_WEST_1 as bucket must be in same region as Rekog
  val s3Client = AmazonS3ClientBuilder
    .standard()
    .withRegion(Regions.EU_WEST_1)
    .withCredentials(new AWSStaticCredentialsProvider(yourAWSCredentials))
    .build()

  def uploadFile(fileName: String) = {
    val fileToUpload = new File(s"tmp/$fileName")

    s3Client.putObject(BUCKET, fileName, fileToUpload)
  }
}
