package io.spring.workshop.stockquotes

import Quote
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class QuoteHandler (val quoteGenerator: QuoteGenerator) {

    fun streamQuotes(serverRequest: ServerRequest) : Mono<ServerResponse> {
        return ok().contentType(MediaType.APPLICATION_STREAM_JSON)
            .body(quoteGenerator.fetchQuoteStream(), Quote::class.java)
    }
}