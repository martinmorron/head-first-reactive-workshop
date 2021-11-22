package io.spring.workshop.tradingservice

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class TradingCompanyController(val tradingCompanyClient: TradingCompanyClient) {

    @GetMapping(value = ["/details"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllCompanies(): Flux<TradingCompany> {
        return tradingCompanyClient.findAllCompanies()
    }

    @GetMapping(value = ["/details/{ticker}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCompanyByTicker(@PathVariable ticker: String): Mono<TradingCompany> {
        return tradingCompanyClient.getTradingCompany(ticker)
    }
}