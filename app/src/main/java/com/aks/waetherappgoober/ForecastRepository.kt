package com.aks.waetherappgoober

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

// Our repository does 2 things: PART A. load data and model into data class
// PART B. Provides data to our activity
class ForecastRepository {
    // PART B. Providing data to our activity
    //To provide data to our activity, we first need an object of LiveData class

    /*?? -> 1. what is MutableLiveData 2. What is it doing here.*/
    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast



    // PART A load data and model into data class
    // Though our repository loads data from N/w or a database but to keep it simple we are loading data statically by
    // generating a list of random temperature bet 0 to 100 and modeling our data class with this list of temperature using a method
    // called loadForecast()
    fun loadForecast(zipCode : String) {
        // generate a list of random temperatures
        val randomTemperature = List(7) { Random.nextFloat().rem(100) * 100 }

        // Mapping this list of temperature and description string to DailyForecast data class.
        //map returns a list of type DailyForecast which is referred by the variable forecastItems.
        val forecastItems   = randomTemperature.map {
            DailyForecast(it, "Partly Cloudy")
        }

        //forecastItems is a list of DailyForecast
        _weeklyForecast.value = forecastItems  //<-- setting up this automatically sets up weeklyForecast
    }


}