package io.spring.workshop.stockquotes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockQuotesApplication

fun main(args: Array<String>) {
	runApplication<StockQuotesApplication>(*args)
}
