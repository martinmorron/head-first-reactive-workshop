package io.spring.workshop.stockquotes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_STREAM_JSON
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.accept


@Configuration
class QuoteRouter {

    @Bean
    fun route(quoteHandler: QuoteHandler): RouterFunction<ServerResponse> {
        return RouterFunctions
            .route(GET("/quotes").and(accept(APPLICATION_STREAM_JSON)), quoteHandler::streamQuotes)
    }
}