package ru.saiushev.testserviceforalphaank.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.ResponseEntity
import ru.saiushev.testserviceforalphaank.models.GifObject
import ru.saiushev.testserviceforalphaank.services.CurrencyService
import ru.saiushev.testserviceforalphaank.services.GifService
import java.math.BigDecimal

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class IntegrationTest(
    @LocalServerPort val port: Int,
    @MockBean
    @Autowired
    val currencyService: CurrencyService,
    @SpyBean
    @Autowired
    val gifService: GifService
) {
    val testRestTemplate = TestRestTemplate()

    @Test
    fun `should return rich gif`() {
        Mockito.`when`(currencyService.getRatesBySymbol("AMD")).thenReturn(BigDecimal(1))
        val responseEntity: ResponseEntity<GifObject> = testRestTemplate.getForEntity(
            "http://localhost:$port/api/rate?symbol=AMD",
            GifObject::class.java
        )
        verify(gifService).getRichGif()
        assertThat(responseEntity.body).isInstanceOf(GifObject::class.java)
    }

    @Test
    fun `should return broke gif`() {
        Mockito.`when`(currencyService.getRatesBySymbol("AMD")).thenReturn(BigDecimal(-1));
        val responseEntity: ResponseEntity<GifObject> = testRestTemplate.getForEntity(
            "http://localhost:$port/api/rate?symbol=AMD",
            GifObject::class.java
        )
        verify(gifService).getBrokeGif()
        assertThat(responseEntity.body).isExactlyInstanceOf(GifObject::class.java)
    }
}