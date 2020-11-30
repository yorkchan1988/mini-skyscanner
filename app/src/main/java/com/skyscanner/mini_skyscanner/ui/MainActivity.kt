package com.skyscanner.mini_skyscanner.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.skyscanner.mini_skyscanner.R
import com.skyscanner.mini_skyscanner.databinding.ActivityMainBinding
import com.skyscanner.mini_skyscanner.ui.MainViewModel
import com.skyscanner.mini_skyscanner.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this@MainActivity, R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)
    }
}