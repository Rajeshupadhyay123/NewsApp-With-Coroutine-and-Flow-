package com.example.newsappadvance.util

import com.example.newsappadvance.model.Article
import com.example.newsappadvance.model.News

sealed class ApiStates{
    object Loading:ApiStates()
    class Failure(val msg:Throwable):ApiStates()
    class Success(val data:News):ApiStates()
    object Empty:ApiStates()
}
