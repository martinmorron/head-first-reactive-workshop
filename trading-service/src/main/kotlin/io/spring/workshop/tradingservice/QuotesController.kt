package io.spring.workshop.tradingservice

import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class QuotesController(var quotesClient: QuotesClient, var tradingCompanyClient: TradingCompanyClient) {

    @GetMapping(value = ["/quotes/feed"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getFeed(): Flux<Quote> {
        return quotesClient.getFeed()
    }

    @GetMapping(value = ["/quotes/summary/{ticker}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getSummary(@PathVariable ticker: String): Mono<TradingCompanySummary> {
        return tradingCompanyClient.getTradingCompany(ticker)
            .zipWith(quotesClient.getLatestQuote(ticker), ::TradingCompanySummary)
    }
}