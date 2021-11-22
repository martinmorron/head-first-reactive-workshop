import java.math.BigDecimal
import java.math.MathContext
import java.time.Instant

class Quote {

    companion object {
        private val MATH_CONTEXT = MathContext(2)
    }

    var ticker: String? = null
    var price: BigDecimal = BigDecimal.ZERO
    var instant = Instant.now()

    constructor()

    constructor(ticker: String?, price: BigDecimal) {
        this.ticker = ticker
        this.price = price
    }

    constructor(ticker: String?, price: Double?) : this(ticker, BigDecimal(price!!, MATH_CONTEXT))

    override fun toString(): String {
        return "Quote{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                ", instant=" + instant +
                '}'
    }
}