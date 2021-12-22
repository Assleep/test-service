package ru.saiushev.testserviceforalphaank.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.saiushev.testserviceforalphaank.models.GifObject
import ru.saiushev.testserviceforalphaank.services.GifService

@RequestMapping("/api/rate")
@RestController
class CurrencyController(@Autowired val gifService: GifService) {
    @GetMapping(produces = ["application/json"])
    fun getGif(@RequestParam("symbol") symbol: String): GifObject {
        return gifService.getGifBySymbolDeltaRates(symbol)
    }
}