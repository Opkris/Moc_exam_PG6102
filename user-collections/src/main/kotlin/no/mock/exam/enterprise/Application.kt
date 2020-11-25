package no.mock.exam.enterprise

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo


@SpringBootApplication
class Application {

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("API for User-Collections")
                .description("REST service to handle the movie bought.")
                .version("1.0")
                .build()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}