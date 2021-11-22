package io.spring.workshop.tradingservice

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.function.Predicate

@Component
class QuotesClient {

    fun getFeed() : Flux<Quote> {
        return WebClient.create("http://localhost:8081")
            .get()
            .uri("/quotes")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .retrieve()
            .bodyToFlux(Quote::class.java)
            .share()
    }

    fun getLatestQuote(ticker : String) : Mono<Quote> {
        val feed = getFeed()
        return feed.filter { q -> q.ticker.equals(ticker, ignoreCase = true) }.next()
            .timeout(Duration.ofSeconds(15), Mono.just(Quote(ticker, 0.0)))
    }
}