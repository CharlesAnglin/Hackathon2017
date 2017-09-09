package services

import java.io.{BufferedInputStream, FileInputStream}
import java.nio.ByteBuffer
import java.util

import utils.Constants._
import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.regions.Regions
import com.amazonaws.services.rekognition.{AmazonRekognitionClient, AmazonRekognitionClientBuilder}
import com.amazonaws.services.rekognition.model._

import scala.io.Source

trait Rekog extends AWSCredentials {

  val rekognitionClient = AmazonRekognitionClientBuilder
    .standard()
    .withRegion(Regions.EU_WEST_1)
    .withCredentials(new AWSStaticCredentialsProvider(yourAWSCredentials))
    .build()

  def createCollection = {
    //TODO: currently can be done via command line, do we want to specifically do it via the app?
  }

  def indexFace(fileName: String, name: String) = {
    val request = new IndexFacesRequest()
      .withCollectionId(COLLECTION)
      .withImage(new Image()
        .withS3Object(new S3Object()
          .withName(fileName)
          .withBucket(BUCKET)))
      .withExternalImageId(name)

    rekognitionClient.indexFaces(request).getFaceRecords
  }

  def searchByFace(fileName: String) = {
    val file = new FileInputStream(s"tmp/$fileName")
    val fChan = file.getChannel()
    val fSize = fChan.size()
    val mBuf = ByteBuffer.allocate(fSize.toInt)
    fChan.read(mBuf)
    mBuf.rewind()
    fChan.close()
    file.close()

    val request = new SearchFacesByImageRequest()
      .withCollectionId(COLLECTION)
      .withImage(new Image()
                    .withBytes(mBuf))

    rekognitionClient.searchFacesByImage(request).getFaceMatches
  }

}
