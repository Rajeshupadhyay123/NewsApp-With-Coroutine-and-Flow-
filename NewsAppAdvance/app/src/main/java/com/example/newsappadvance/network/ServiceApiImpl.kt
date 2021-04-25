package com.example.newsappadvance.network

import com.example.newsappadvance.model.Article
import com.example.newsappadvance.model.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServiceApiImpl @Inject constructor(private val api: ServiceApi) {

    suspend fun getNews():News{
        return api.getNews()
    }
}