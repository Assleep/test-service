package ru.saiushev.testserviceforalphaank.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class GifDataObject(
    @JsonIgnoreProperties("type")
    val type: String,
    @JsonIgnoreProperties("id")
    val id: String,
    val url: String,
    @JsonIgnoreProperties("slug")
    val slug: String,
    @JsonIgnoreProperties("bitly_gif_url")
    val bitly_gif_url: String,
    @JsonIgnoreProperties("bitly_url")
    val bitly_url: String,
    @JsonIgnoreProperties("embed_url")
    val embed_url: String,
    @JsonIgnoreProperties("username")
    val username: String,
    @JsonIgnoreProperties("source")
    val source: String,
    @JsonIgnoreProperties("title")
    val title: String,
    @JsonIgnoreProperties("rating")
    val rating: String,
    @JsonIgnoreProperties("content_url")
    val content_url: String,
    @JsonIgnoreProperties("source_tld")
    val source_tld: String,
    @JsonIgnoreProperties("source_post_url")
    val source_post_url: String,
    @JsonIgnoreProperties("is_sticker")
    val is_sticker: Boolean,
    @JsonIgnoreProperties("import_datetime")
    val import_datetime: String,
    @JsonIgnoreProperties("trending_datetime")
    val trending_datetime: String,
    @JsonIgnoreProperties("images")
    val images: Any
)
