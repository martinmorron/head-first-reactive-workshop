package io.spring.workshop.stockdetails

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class TradingCompanyCommandLineRunner(var repository: TradingCompanyRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {

        val list = listOf(
            TradingCompany("1", "D-CTXS", "CTXS"),
            TradingCompany("2","D-DELL", "DELL"),
            TradingCompany("3","D-GOOG", "GOOG"),
            TradingCompany("4","D-MSFT", "MSFT"),
            TradingCompany("5","D-ORCL", "ORCL"),
            TradingCompany("6","D-RHT", "RHT"),
            TradingCompany("7","D-VMW", "VMW")
        )

        repository.insert(list).blockLast(Duration.ofSeconds(1))
    }
}