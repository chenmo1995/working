package com.fudn.mybatisplusdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author fdn
 * @since 2021-08-30 17:51
 */
@Configuration
public class Swagger2Config {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)        // 文档类型，不用理会，就是用SWAGGER_2
                .apiInfo(apiInfo()).select()                  // 接口文档信息设置，抽取到apiInfo()进行设置
                .apis(RequestHandlerSelectors.any()).build(); // Controller的扫描规则：对所有Controller生成接口文档
    }

    /**
     * 接口文档信息设置
     * http://localhost:9999/swagger-ui.html
     * http://localhost:9999/doc.html
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("郭小郭有限公司私密文档")
                .contact(new Contact("fudn", null, "1240589270@qq.com"))
                .description("测试swagger2文档")
                .version("1.0")
                .build();
    }

}
