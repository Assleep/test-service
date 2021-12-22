package ru.saiushev.testserviceforalphaank.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.SpringQueryMap
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import ru.saiushev.testserviceforalphaank.models.GifObject

@FeignClient(value = "gif", url = "\${gif.api-url}")
interface GifClientInterface {
    @RequestMapping(method = [RequestMethod.GET], value = ["random"])
    fun getGif(@SpringQueryMap queryMap: Map<String, Any>): ResponseEntity<GifObject>
}