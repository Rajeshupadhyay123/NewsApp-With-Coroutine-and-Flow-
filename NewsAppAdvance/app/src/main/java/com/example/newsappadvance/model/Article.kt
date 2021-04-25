package com.example.newsappadvance.model

data class Article(
    val source:Source,
    val author:String,
    val title:String,
    val content:String,
    val urlToImage:String
)

data class Source(
    val id:String,
    val name:String
)