package ru.saiushev.testserviceforalphaank.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.saiushev.testserviceforalphaank.feign.GifClientInterface
import ru.saiushev.testserviceforalphaank.models.GifObject
import java.math.BigDecimal

@Service
class GifService(
    @Autowired
    private val gifClientInterface: GifClientInterface,
    @Value("\${gif.app-key}")
    private val APP_KEY: String,
    @Autowired
    private val currencyService: CurrencyService
) {
    fun getRichGif(): GifObject =
        gifClientInterface.getGif(
            mapOf(
                "api_key" to APP_KEY,
                "tag" to "rich"
            )
        ).body!!

    fun getBrokeGif(): GifObject =
        gifClientInterface.getGif(
            mapOf(
                "api_key" to APP_KEY,
                "tag" to "broke"
            )
        ).body!!

    fun getGifBySymbolDeltaRates(symbol: String): GifObject {
        return if (currencyService.getRatesBySymbol(symbol) > BigDecimal.ZERO) getRichGif() else getBrokeGif()
    }
}