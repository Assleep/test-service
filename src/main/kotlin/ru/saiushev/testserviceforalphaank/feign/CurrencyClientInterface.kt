package ru.saiushev.testserviceforalphaank.feign


import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.SpringQueryMap
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import ru.saiushev.testserviceforalphaank.models.Rate

@FeignClient(value = "currency", url = "\${currency.api-url}")
interface CurrencyClientInterface {
    @RequestMapping(method = [RequestMethod.GET], value = ["historical/{date}.json"])
    fun getRates(@SpringQueryMap queryMap: Map<String, Any>, @PathVariable("date") date: String): ResponseEntity<Rate>
}
