package com.skyscanner.mini_skyscanner.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minimoneybox.ui.main.useraccounts.FlightsRecyclerAdapter
import com.skyscanner.mini_skyscanner.R
import com.skyscanner.mini_skyscanner.databinding.ActivityMainBinding
import com.skyscanner.mini_skyscanner.ui.MainViewModel
import com.skyscanner.mini_skyscanner.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    lateinit var viewModel: MainViewModel

    lateinit var rvFlights: RecyclerView

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: FlightsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this@MainActivity, R.layout.activity_main)

        // init recycleView
        rvFlights = binding.rvFlights
        rvFlights.addItemDecoration(
            DividerItemDecoration(
                rvFlights.context,
                DividerItemDecoration.VERTICAL
            )
        )
        initRecyclerView()

        viewModel.itineraries.observe(this, Observer {
            adapter.setItineraries(it)
        })
        viewModel.getFlights()
    }

    private fun initRecyclerView() {
        rvFlights.setLayoutManager(LinearLayoutManager(this))
        rvFlights.setAdapter(adapter)
    }
}