package ru.saiushev.testserviceforalphaank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class TestServiceForAlphaankApplication

fun main(args: Array<String>) {
	runApplication<TestServiceForAlphaankApplication>(*args)
}
