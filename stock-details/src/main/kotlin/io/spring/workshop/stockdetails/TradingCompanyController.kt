package io.spring.workshop.stockdetails

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class TradingCompanyController (val repository : TradingCompanyRepository) {

    @GetMapping(value = ["/details"], produces = ["application/json"])
    @ResponseBody
    fun details(): Flux<TradingCompany> {
        return repository.findAll()
    }

    @GetMapping(value = ["/details/{ticker}"], produces = ["application/json"])
    @ResponseBody
    fun byTicker(@PathVariable ticker : String): Mono<TradingCompany> {
        return repository.findByTicker(ticker)
    }
}