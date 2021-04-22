package com.cafe24.kangk0269.api;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UploadObject {
  
  
  
  public void uploadObject() throws IOException {
	  // The ID of your GCP project
	  String projectId = "dogwood-cinema-309905";
	  
	  // The ID of your GCS bucket
	  String bucketName = "ksmart-project";
	  
	  // The ID of your GCS object
	  String objectName = "application.properties";
	  
	  // The path to your file to upload
	  String filePath = "C:\\fakepath\\application.properties";
	  
	  Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
	  BlobId blobId = BlobId.of(bucketName, objectName);
	  BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
	  storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));
	  
	  System.out.println(
			  "File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
  }
  
  
  
  
  
  
  
}