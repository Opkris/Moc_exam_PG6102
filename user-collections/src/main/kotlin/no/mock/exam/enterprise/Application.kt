package no.mock.exam.enterprise

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket



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