package io.spring.workshop.stockquotes

import Quote
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.math.BigDecimal
import java.math.MathContext
import java.time.Duration
import java.time.Instant
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Component
class QuoteGenerator {
    private val mathContext = MathContext(2)
    private val random: Random = Random()
    private val prices: MutableList<Quote> = ArrayList()
    private val quoteStream: Flux<Quote>

    init {
        initializeQuotes()
        quoteStream = getQuoteStream()
    }

    fun fetchQuoteStream(): Flux<Quote> {
        return quoteStream
    }

    private fun initializeQuotes() {
        prices.add(Quote("CTXS", 82.26))
        prices.add(Quote("DELL", 63.74))
        prices.add(Quote("GOOG", 847.24))
        prices.add(Quote("MSFT", 65.11))
        prices.add(Quote("ORCL", 45.71))
        prices.add(Quote("RHT", 84.29))
        prices.add(Quote("VMW", 92.21))
    }

    private fun getQuoteStream(): Flux<Quote> {
        return Flux.interval(Duration.ofMillis(200))
            .onBackpressureDrop()
            .map { i: Long ->
                generateQuotes()
            }
            .flatMapIterable { quotes: List<Quote> -> quotes }
            .share()
    }

    private fun generateQuotes(): List<Quote> {
        val instant = Instant.now()
        return prices.stream()
            .map { baseQuote: Quote ->
                val priceChange = baseQuote.price
                    .multiply(BigDecimal(0.05 * random.nextDouble()), mathContext)
                val result = Quote(baseQuote.ticker, baseQuote.price.add(priceChange))
                result.instant = instant
                result
            }
            .collect(Collectors.toList())
    }

}