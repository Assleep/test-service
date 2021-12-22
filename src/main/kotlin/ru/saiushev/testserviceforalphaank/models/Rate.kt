package ru.saiushev.testserviceforalphaank.models

data class Rate(
    val disclaimer: String,
    val license: String,
    val timestamp: Long,
    val base: String,
    val rates: Any
)
