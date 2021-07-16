package com.aks.waetherappgoober

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

// Our repository does 2 things: PART a. load data and model into data class
// PART b. Provides data to our activity
class ForecastRepository {
    //region PART b. Providing data to our activity
    //To provide data to our activity, we first need an object of LiveData class

    /*?? -> 1. what is MutableLiveData 2. What is it doing here.*/
    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast
    //endregion

    //region PART a load data and model into data class
    // Though our repository loads data from N/w or a database but to keep it simple we are loading data statically by
    // generating a list of random temperature bet 0 to 100 and modeling our data class with this list of temperature using a method
    // called loadForecast()
    fun loadForecast() {
        // generate a list of random temperatures
        val randomTemperature = List(7) { Random.nextFloat().rem(100) * 100 }

        // Mapping this list of temperatures to DailyForecast data class with description String
        val forecastItems = randomTemperature.map {
            DailyForecast(it, "Partly Cloudy")
        }
        //forecastItems is a list of DailyForecast
        _weeklyForecast.value = forecastItems  //<-- setting up this automatically sets up weeklyForecast
    }

//endregion part a

}