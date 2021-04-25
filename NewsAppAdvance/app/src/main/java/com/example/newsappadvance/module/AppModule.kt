package com.example.newsappadvance.module

import com.example.newsappadvance.network.ServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl()="https://newsapi.org/"

    @Provides
    @Singleton
    fun provideRetrofit(@BaseUrl baseUrl:String):Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideServiceApi(retrofit: Retrofit):ServiceApi{
        return retrofit.create(ServiceApi::class.java)
    }
}
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl
