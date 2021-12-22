package ru.saiushev.testserviceforalphaank.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class GifObject(
    val data: GifDataObject,
    @JsonIgnoreProperties("meta")
    val meta: Any

)
