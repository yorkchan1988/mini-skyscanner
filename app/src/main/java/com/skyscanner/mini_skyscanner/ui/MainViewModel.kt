package com.skyscanner.mini_skyscanner.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skyscanner.mini_skyscanner.models.Itinerary
import com.skyscanner.mini_skyscanner.network.ApiResource
import com.skyscanner.mini_skyscanner.repository.FlightsRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val flightsRepository: FlightsRepository
): ViewModel() {

    // LiveData
    var apiStatus: MutableLiveData<ApiResource<List<Itinerary>>> = MutableLiveData()

    var itineraries: MutableLiveData<List<Itinerary>> = MutableLiveData()

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.let {
            compositeDisposable.dispose()
        }
    }

    fun getFlights() {
        flightsRepository.getFlights().subscribeBy(
            onNext = {
                apiStatus.postValue(it)
                when (it.status) {
                    ApiResource.ApiStatus.SUCCESS -> {
                        it.data?.let {
                            itineraries.postValue(it)
                        }
                    }
                    ApiResource.ApiStatus.ERROR -> {
                        it.error?.let {
                            apiStatus.postValue(ApiResource.Error<List<Itinerary>>(null, it))
                        }
                    }
                }
            },
            onError = {
                apiStatus.postValue(ApiResource.Error<List<Itinerary>>(null, it))
            }
        )
    }
}