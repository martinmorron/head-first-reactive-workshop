package io.spring.workshop.stockdetails

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class TradingCompany (@Id var id: String, var description : String, var ticker : String) {

    fun TradingCompany(description: String?, ticker: String?) {
        this.description = description!!
        this.ticker = ticker!!
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as TradingCompany
        return if (id != that.id) false else description == that.description
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}