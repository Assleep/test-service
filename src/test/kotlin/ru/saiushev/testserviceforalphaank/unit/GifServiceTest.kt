package ru.saiushev.testserviceforalphaank.unit

import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean
import ru.saiushev.testserviceforalphaank.services.CurrencyService
import ru.saiushev.testserviceforalphaank.services.GifService
import java.math.BigDecimal

@SpringBootTest
class GifServiceTest(
    @MockBean
    @Autowired
    val currencyService: CurrencyService,
    @SpyBean
    @Autowired
    val gifService: GifService
){

        @Test
        fun `Broke`() {
            Mockito.`when`(currencyService.getRatesBySymbol(anyString())).thenReturn(BigDecimal(-1))
            gifService.getGifBySymbolDeltaRates("")
            verify(gifService).getBrokeGif()
        }
        @Test
        fun `Rich`() {
            Mockito.`when`(currencyService.getRatesBySymbol(anyString())).thenReturn(BigDecimal(1))
            gifService.getGifBySymbolDeltaRates("")
            verify(gifService).getRichGif()
        }
    }
