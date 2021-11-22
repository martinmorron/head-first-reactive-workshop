package io.spring.workshop.stockdetails

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockDetailsApplication

fun main(args: Array<String>) {
	runApplication<StockDetailsApplication>(*args)
}
