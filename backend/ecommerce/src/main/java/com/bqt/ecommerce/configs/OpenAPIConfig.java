package com.bqt.ecommerce.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/*
@Tag Đánh dấu 1 class là nơi chứa các API
@Schema Bổ sung các thông tin cho
@Operation Mô tả cho một API và response của nó
@Parameter Mô tả các parameter
@ApiResponse Mô tả status code của response
@ApiResponses Mô tả danh sách các status code của response
*/
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        Server devServer = new Server();
        devServer.setUrl("http://localhost:9000");
        devServer.setDescription("Server URL in Development environment");


        License mitLicense = new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html");

        Info info = new Info()
                .title("Tutorial Management API")
                .version("1.0")
                .description("This API exposes endpoints to manage tutorials.")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
