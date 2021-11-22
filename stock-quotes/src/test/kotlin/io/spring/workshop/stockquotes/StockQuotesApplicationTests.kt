package io.spring.workshop.stockquotes

import Quote
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient


@ExtendWith(SpringExtension::class) //  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StockQuotesApplicationTests {
	// Spring Boot will create a `WebTestClient` for you,
	// already configure and ready to issue requests against "localhost:RANDOM_PORT"
	@Autowired
	private val webTestClient: WebTestClient? = null
	@Test
	fun fetchQuotes() {
		val result: MutableList<Quote>? = webTestClient!! // We then create a GET request to test an endpoint
			.get().uri("/quotes")
			.accept(MediaType.APPLICATION_STREAM_JSON)
			.exchange() // and use the dedicated DSL to test assertions against the response
			.expectStatus().isOk
			.expectHeader().contentType(MediaType.APPLICATION_STREAM_JSON)
			.returnResult<Quote>(Quote::class.java)
			.getResponseBody()
			.take(20)
			.collectList()
			.block()

		assertThat(result).allSatisfy { quote -> assertThat(quote.price).isPositive() }
	}
}
