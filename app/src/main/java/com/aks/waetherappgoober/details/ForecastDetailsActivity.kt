package com.aks.waetherappgoober.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.aks.waetherappgoober.R
import com.aks.waetherappgoober.formatTempForDisplay

class ForecastDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_deatails)

        //This can set title for an Activity.
        setTitle(R.string.title_for_forecastDetailActivity)

        val tempText = findViewById<TextView>(R.id.tempTextIdForecastDetailActivity)
        val descriptionText = findViewById<TextView>(R.id.descriptionTextIdForecastDetailActivity)

        //ACCESSING data from previous Activity, here MainActivity.
        //Any Activity has an intent( the intent using which this activity has been launched ). using
        //passForecastDetailsUsingIntent an intent is being used to access this activity.
        //this intent will be used to access data from previous Activity.
        //for referencing the intent we have 'intent' idiom in Kotlin. or we can use a kind of getter called getIntent()
        val temp = intent.getFloatExtra("key_temp", 0f)

        tempText.text = formatTempForDisplay(temp)
        descriptionText.text = intent.getStringExtra("key_description")
    }
}