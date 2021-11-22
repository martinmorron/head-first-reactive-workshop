package io.spring.workshop.tradingservice

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class TradingCompanyClient {

    fun findAllCompanies(): Flux<TradingCompany> {
        return WebClient.create("http://localhost:8082")
            .get()
            .uri("/details")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(TradingCompany::class.java)
    }

    fun getTradingCompany(ticker: String): Mono<TradingCompany> {
        return WebClient.create("http://localhost:8082")
            .get()
            .uri("/details/{ticker}", ticker)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(TradingCompany::class.java)
            .switchIfEmpty(Mono.error(TickerNotFoundException("Ticker not found: $ticker")))
    }
}