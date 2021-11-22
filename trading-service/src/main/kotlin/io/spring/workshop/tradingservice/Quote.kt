package io.spring.workshop.tradingservice

import java.math.BigDecimal
import java.math.MathContext
import java.time.Instant

class Quote() {

    companion object {
        val MATH_CONTEXT : MathContext = MathContext(2)
    }

    var ticker: String = ""

    var price: BigDecimal = BigDecimal.ZERO

    var instant : Instant = Instant.now()

    constructor(ticker: String, price: BigDecimal) : this() {
        this.ticker = ticker
        this.price = price
    }

    constructor(ticker: String, price: Double) : this() {
        this.ticker = ticker
        this.price = BigDecimal(price, MATH_CONTEXT)
    }

    override fun toString(): String {
        return "Quote(ticker='$ticker', price=$price, instant=$instant)"
    }


}


/*
package io.spring.workshop.stockquotes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;

public class Quote {



	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	@Override
	public String toString() {
		return "Quote{" +
				"ticker='" + ticker + '\'' +
				", price=" + price +
				", instant=" + instant +
				'}';
	}
}
 */