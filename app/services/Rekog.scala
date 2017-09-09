package services

import java.io.{BufferedInputStream, FileInputStream}
import java.nio.ByteBuffer
import utils.Constants._
import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.regions.Regions
import com.amazonaws.services.rekognition.{AmazonRekognitionClient, AmazonRekognitionClientBuilder}
import com.amazonaws.services.rekognition.model.{Attribute, DetectFacesRequest, Image, S3Object}

import scala.io.Source

trait Rekog extends GetAccessKeys {

  //TODO: want to use indexFaces on each uploaded image to store them in Rekog (can add unquie id, e.g. name for each img).
  //Then use searchFacesByImage to find faces which match (and hence the name).
  //Also use createCollection etc to manage your collection of indexed faces.

  //"To access images stored in an Amazon S3 bucket, you must have permission to access object in the
//  S3 bucket. With this permission, Amazon Rekognition can download images from the S3 bucket"

  lazy val (accessKey, secretKey) = getAccessKeys

  val yourAWSCredentials = new BasicAWSCredentials(accessKey, secretKey)

  val rekognitionClient = AmazonRekognitionClientBuilder
    .standard()
    .withRegion(Regions.EU_WEST_1)
    .withCredentials(new AWSStaticCredentialsProvider(yourAWSCredentials))
    .build()

  def createCollection = {
    //TODO: currently can be done via command line, do we want to specifically do it via the app?
  }

  def indexFaces = {

  }







  def detectFaces = {
    val fileName = "charlie5.png"

    val request = new DetectFacesRequest()
      .withImage(new Image()
        .withS3Object(new S3Object()
          .withName(fileName)
          .withBucket(BUCKET)))
      .withAttributes(Attribute.ALL)

    val a = rekognitionClient.detectFaces(request).getFaceDetails
    a
  }


  // ~~~~~ Upload file from local: ~~~~~
  //    val file = new FileInputStream("tmp/charlie2.png")
  //    val fChan = file.getChannel();
  //    val fSize = fChan.size();
  //    val mBuf = ByteBuffer.allocate(fSize.toInt)
  //    fChan.read(mBuf);
  //    mBuf.rewind()
  //    fChan.close();
  //    file.close();
  //
  //    val request = new DetectFacesRequest()
  //          .withImage(new Image()
  //            .withBytes(mBuf))
  //          .withAttributes(Attribute.ALL)

}
