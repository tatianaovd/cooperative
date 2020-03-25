package cooperative.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Value(value = "${springfox.swagger2.enable.docs:true}")
    private boolean enableSwaggerDocumentation;

    @Bean
    public Docket cooperativeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(cooperativeApiInfo())
                .enable(enableSwaggerDocumentation)
                .useDefaultResponseMessages(false)
                .select()
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo cooperativeApiInfo() {
        String description = "Cooperative";
        return new ApiInfoBuilder()
                .title("Cooperative")
                .description(description)
                .build();
    }
}
