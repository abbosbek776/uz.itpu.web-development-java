package uk.com.bookcloud;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "Book Cloud", description = "Backend app for bookcloud"))
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@ComponentScan(basePackages = {"uk.com.bookcloud","uk.com.bookcloud.mapper"})
public class SpringBootBookcloudApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootBookcloudApplication.class, args);
    }


}
