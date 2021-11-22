package io.spring.workshop.tradingservice

class TradingCompany (var id: String, var description : String, var ticker : String) {

    fun TradingCompany() {}

    fun TradingCompany(id: String?, description: String?, ticker: String?) {
        this.id = id!!
        this.description = description!!
        this.ticker = ticker!!
    }

    fun TradingCompany(description: String?, ticker: String?) {
        this.description = description!!
        this.ticker = ticker!!
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as TradingCompany
        return if (id != that.id) false else description == that.description
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}