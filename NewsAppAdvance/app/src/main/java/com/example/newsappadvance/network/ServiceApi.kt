package com.example.newsappadvance.network

import com.example.newsappadvance.model.Article
import com.example.newsappadvance.model.News
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY="e618a4be2e5a4ea9971670ea91e2cff5"

interface ServiceApi {

    //domain==wsj.com
    @GET("v2/everything?domains=wsj.com&apiKey=$API_KEY")
    suspend fun getNews():News
}