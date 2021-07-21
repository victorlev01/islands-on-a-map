/*
 * SwaggerConfig.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.advanon.test"))
                .paths(PathSelectors.any())
                .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder().title("Maps and islands application API")
                .description("Test api description")
                .contact("victorlev01@gmail.com")
                .licenseUrl("https://opensource.org/licenses/MIT").version("1.0").build()
    }
}