package ru.saiushev.testserviceforalphaank.unit

import org.assertj.core.api.AssertionsForInterfaceTypes
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ResponseStatusException
import ru.saiushev.testserviceforalphaank.feign.CurrencyClientInterface
import ru.saiushev.testserviceforalphaank.models.Rate
import ru.saiushev.testserviceforalphaank.services.CurrencyService
import java.math.BigDecimal

@SpringBootTest
class CurrencyServiceTest {
    final val currencyClient: CurrencyClientInterface = mock(CurrencyClientInterface::class.java)
    val currencyService = CurrencyService(currencyClient, " ", "")

    @Nested
    inner class RightResponse {
        @Test
        fun `200 Status`() {
            val rightResponse = Rate(
                "Usage subject to terms: https://openexchangerates.org/terms",
                "https://openexchangerates.org/license",
                1639699198,
                "USD",
                "AMD=323.323"
            )
            `when`(currencyClient.getRates(anyMap(), anyString())).thenReturn(
                ResponseEntity(
                    rightResponse,
                    HttpStatus.OK
                )
            )
            AssertionsForInterfaceTypes.assertThat(currencyService.getRatesBySymbol("AMD"))
                .isInstanceOf(BigDecimal::class.java)
        }
    }

    @Nested
    inner class WrongResponse {
        @Test
        fun `404 Status`() {
            `when`(
                currencyClient.getRates(
                    anyMap(),
                    anyString()
                )
            ).thenReturn(ResponseEntity(HttpStatus.NOT_FOUND))
            assertThrows<ResponseStatusException> { currencyService.getTodayCurrencyRate("") }
            assertThrows<ResponseStatusException> { currencyService.getYesterdayCurrencyRate("") }
        }

        @Test
        fun `500 Status`() {
            `when`(
                currencyClient.getRates(
                    anyMap(),
                    anyString()
                )
            ).thenReturn(ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR))
            assertThrows<ResponseStatusException> { currencyService.getTodayCurrencyRate("") }
            assertThrows<ResponseStatusException> { currencyService.getYesterdayCurrencyRate("") }
        }
    }
}