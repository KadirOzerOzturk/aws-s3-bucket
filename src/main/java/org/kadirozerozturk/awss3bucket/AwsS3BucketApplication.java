package org.kadirozerozturk.awss3bucket;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Log4j2
public class AwsS3BucketApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsS3BucketApplication.class, args);
    }
    @Bean
    public ApplicationRunner applicationRunner(S3Service s3Service) {
        return args -> {
            log.info("Spring Boot AWS S3 integration...");

            try {
                var s3Object = s3Service.getFile("jvm.png");
                log.info(s3Object.getKey());
            } catch (AmazonS3Exception e) {
                log.error(e.getMessage());
            }
        };
    }
}
