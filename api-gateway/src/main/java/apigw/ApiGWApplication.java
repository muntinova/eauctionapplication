package apigw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class ApiGWApplication {
    public static void main(String[] args) {
        log.info("api gateway");
        SpringApplication.run(ApiGWApplication.class,args);
    }
}
