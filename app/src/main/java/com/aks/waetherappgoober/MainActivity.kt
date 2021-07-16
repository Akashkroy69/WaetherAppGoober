package com.aks.waetherappgoober

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private val forecastRepository = ForecastRepository()

    //region set up methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val enterZipCodeField: EditText = findViewById(R.id.zipCodeId)
        val howIsWeatherButtonId: Button = findViewById(R.id.howIsWeatherButtonId)
        //Setting up On-Click Listener.
        howIsWeatherButtonId.setOnClickListener {
            val zipCode = enterZipCodeField.text.toString()
            if (zipCode.length == 6) forecastRepository.loadForecast()
            else Toast.makeText(this, R.string.zipcode_error_message, Toast.LENGTH_SHORT).show()
        }
        //creating an observer
        val weeklyForecastObserver = Observer<List<DailyForecast>> {
            //It is used to update RecyclerView and lifecycle adapter.
            Toast.makeText(this,"Forecast Loaded",Toast.LENGTH_SHORT).show()
        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    //endregion set up methods
    // region tear up methods
    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    //endregion tear up methods
}