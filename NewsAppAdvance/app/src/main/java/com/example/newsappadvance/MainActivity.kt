package com.example.newsappadvance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappadvance.adapter.NewsAdapter
import com.example.newsappadvance.databinding.ActivityMainBinding
import com.example.newsappadvance.util.ApiStates
import com.example.newsappadvance.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var viewModel: NewsViewModel
    lateinit var recyclerAdapter:NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel=ViewModelProvider(this).get(NewsViewModel::class.java)

        initRecyclerView()
        lifecycleScope.launchWhenCreated {
            viewModel.mutableStateFlow.collect{
                when(it){
                    is ApiStates.Loading->{
                        binding.recycler.isVisible=false
                        binding.progressBar.isVisible=true
                        binding.statusConnection.isVisible=false
                    }

                    is ApiStates.Failure -> {
                        binding.recycler.isVisible = false
                        binding.progressBar.isVisible = false
                        binding.statusConnection.isVisible=true
                        Toast.makeText(this@MainActivity, "${it.msg}", Toast.LENGTH_SHORT).show()
                        Log.i("main", "Error is ${it.msg}")
                    }

                    is ApiStates.Success->{
                        binding.recycler.isVisible=true
                        binding.progressBar.isVisible=false
                        binding.statusConnection.isVisible=false
                        recyclerAdapter.submitList(it.data.articles)
                        Log.i("main", "Value is  ${it.data}")
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        recyclerAdapter= NewsAdapter()
        binding.recycler.apply{
            adapter=recyclerAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)
        }
    }
}