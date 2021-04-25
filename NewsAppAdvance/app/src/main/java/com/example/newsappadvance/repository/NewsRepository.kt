package com.example.newsappadvance.repository

import com.example.newsappadvance.model.Article
import com.example.newsappadvance.model.News
import com.example.newsappadvance.network.ServiceApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepository @Inject constructor(private val serviceApiImpl: ServiceApiImpl) {

    fun getNews():Flow<News>{
        return flow {
            val response=serviceApiImpl.getNews()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}