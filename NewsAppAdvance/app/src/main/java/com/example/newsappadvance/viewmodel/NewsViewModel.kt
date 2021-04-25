package com.example.newsappadvance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappadvance.repository.NewsRepository
import com.example.newsappadvance.util.ApiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(val newsRepository: NewsRepository):ViewModel() {

    private val _mutableStateFlow=MutableStateFlow<ApiStates>(ApiStates.Empty)
    val mutableStateFlow:MutableStateFlow<ApiStates>
    get() = _mutableStateFlow

    init {
        getNews()
    }

    fun getNews(){
        viewModelScope.launch {
            _mutableStateFlow.value=ApiStates.Loading
            newsRepository.getNews()
                .catch { e->
                    _mutableStateFlow.value=ApiStates.Failure(e)
                }
                .collect { data->
                    _mutableStateFlow.value=ApiStates.Success(data)
                }
        }
    }
}