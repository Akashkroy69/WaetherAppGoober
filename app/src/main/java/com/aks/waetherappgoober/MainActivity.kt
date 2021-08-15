package com.aks.waetherappgoober

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //a reference for the forecast Repository.
    private val forecastRepository = ForecastRepository()

    //region set up methods
    override fun onCreate(savedInstanceState: Bundle?) {

        //Setting up for inflating a layout.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Finding ids for necessary view fields from activity_main.xml
        val enterZipCodeField: EditText = findViewById(R.id.zipCodeId)
        val howIsWeatherButtonId: Button = findViewById(R.id.howIsWeatherButtonId)


        //Setting up On-Click Listener for the button
        howIsWeatherButtonId.setOnClickListener {
            val zipCode = enterZipCodeField.text.toString()
            if (zipCode.length == 6) forecastRepository.loadForecast(zipCode)
            else Toast.makeText(this, R.string.zipcode_error_message, Toast.LENGTH_SHORT).show()
        }


        //setting up Recycler view
        // For setting up recycler view we need to: 1. create a reference for RV, 2. set up LayOut Manger
        // 3. Set up RV Adapter-> For Adapter we need to set up : 1. Adapter, 2. ViewHolder 3. Item callback

        //1. Creating a reference for recycler view.
        val forecastItemsRVId: RecyclerView = findViewById(R.id.forecastItemRVId)
        //2.setting up RV's layoutManager Property as Linear Layout Manager
        forecastItemsRVId.layoutManager = LinearLayoutManager(this)
        //3. Adapter. We created and set up the system for Adapter and ViewHolder which is needed by the RecyclerView
        //see implementation in DailyForecastAdapter() class.
        //A ref for the Adapter class
        val dailyForecastAdapter = DailyForecastAdapter() { forecastItem ->
            val message = getString(
                R.string.string_format_for_toasting_values_fromForecastItem,
                forecastItem.temperature,
                forecastItem.description
            )
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
        forecastItemsRVId.adapter = dailyForecastAdapter

        //creating an observer in context of LveData
        val weeklyForecastObserver = Observer<List<DailyForecast>> {
            //This field is used to update RecyclerView and lifecycle adapter.
            //Toast.makeText(this, "Forecast Loaded", Toast.LENGTH_SHORT).show()

            //we have an RV adapter.
            dailyForecastAdapter.submitList(it)
        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)
    }


    //region lifecycle's other methods
    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    //endregion lifecycle's other methods.
}