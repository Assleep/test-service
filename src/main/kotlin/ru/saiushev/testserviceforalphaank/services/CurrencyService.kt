package ru.saiushev.testserviceforalphaank.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.saiushev.testserviceforalphaank.feign.CurrencyClientInterface
import java.math.BigDecimal
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
class CurrencyService(
    @Autowired val currencyClientInterface: CurrencyClientInterface,
    @Value("\${currency.app-id}")
    private val APP_ID: String,
    @Value("\${currency.base-currency}")
    private val BASE: String

) {
    fun getCurrencyRateByDate(symbol: String, secondsToSubtract: Long): BigDecimal {
        val response = currencyClientInterface.getRates(
            mapOf("app_id" to APP_ID, "base" to BASE, "symbols" to symbol),
            getDate(secondsToSubtract)
        )
        if (response.statusCode.is2xxSuccessful) {
            val price = getPriceFromJson(response.body?.rates.toString())
            return BigDecimal(price)
        } else {
            throw ResponseStatusException(response.statusCode)
        }
    }

    fun getYesterdayCurrencyRate(symbol: String): BigDecimal {
        return getCurrencyRateByDate(symbol, 86400)
    }

    fun getTodayCurrencyRate(symbol: String): BigDecimal {
        return getCurrencyRateByDate(symbol, 0)
    }

    fun getRatesBySymbol(symbol: String): BigDecimal = getTodayCurrencyRate(symbol) - getYesterdayCurrencyRate(symbol)


    private fun getDate(secondsToSubtract: Long): String = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        .withZone(ZoneId.of("UTC"))
        .format(Instant.now().minusSeconds(secondsToSubtract))

    private fun getPriceFromJson(rates: String) = rates.substring(rates.indexOf("=") + 1, rates.length - 1)
}
