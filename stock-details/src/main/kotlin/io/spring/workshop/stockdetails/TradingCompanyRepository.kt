package io.spring.workshop.stockdetails

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface TradingCompanyRepository : ReactiveMongoRepository<TradingCompany, String> {

    fun findByTicker(ticker : String) : Mono<TradingCompany>
}