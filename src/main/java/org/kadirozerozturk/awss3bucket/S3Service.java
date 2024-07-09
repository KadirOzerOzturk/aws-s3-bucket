package org.kadirozerozturk.awss3bucket;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Log4j2
public class S3Service {

    private AmazonS3 s3client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public void uploadFile(String keyName, MultipartFile file) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        var putObjectResult = s3client.putObject(bucketName, keyName, file.getInputStream(), metadata);
        log.info("Uploaded file with key: " + keyName + " and ETag: " + putObjectResult.getETag());
    }

    public S3Object getFile(String keyName) {
        log.info("Fetching file with key: " + keyName);
        return s3client.getObject(bucketName, keyName);
    }
}
