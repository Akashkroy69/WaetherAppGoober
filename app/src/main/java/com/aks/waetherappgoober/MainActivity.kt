package com.aks.waetherappgoober

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aks.waetherappgoober.details.ForecastDetailsActivity
import com.aks.waetherappgoober.location.LocationEntryFragment

class MainActivity : AppCompatActivity() {
    val TAG = "weatherLogCat"

    //a reference for the forecast Repository.
    private val forecastRepository = ForecastRepository()

    //A reference for TempDisplaySettingManager
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    //region set up methods
    override fun onCreate(savedInstanceState: Bundle?) {

        //Setting up for inflating a layout.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Instantiating tempDisplaySettingManager to be used in the adapter and ViewHolder
        tempDisplaySettingManager = TempDisplaySettingManager(this)


        //Finding ids for necessary view fields from activity_main.xml
        val enterZipCodeField: EditText = findViewById(R.id.zipCodeId)
        val howIsWeatherButtonId: Button = findViewById(R.id.howIsWeatherButtonId)


        //Setting up On-Click Listener for the button
        howIsWeatherButtonId.setOnClickListener {
            val zipCode = enterZipCodeField.text.toString()
            if (zipCode.length == 6) forecastRepository.loadForecast(zipCode)
            else Toast.makeText(this, R.string.zipcode_error_message, Toast.LENGTH_SHORT).show()
        }

        Log.d(TAG, "In MainActivity: onCreate")


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
        val dailyForecastAdapter = DailyForecastAdapter(tempDisplaySettingManager) { forecastItem ->
            val message = getString(
                R.string.string_format_for_toasting_values_fromForecastItem,
                forecastItem.temperature,
                forecastItem.description
            )
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            passForecastDetailsUsingIntent(forecastItem)
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

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentLocationEntryId, LocationEntryFragment())
            .commit()
    }

    //method for navigating to a new Activity using an intent.
    private fun passForecastDetailsUsingIntent(forecastItem: DailyForecast) {
        val intentForForecastDetailsActivity = Intent(this, ForecastDetailsActivity::class.java)

        //for attaching extra values and data with the intent which is being used to invoke an Activity, here ForecastDetailsActivity
        //here we are going to pass temperature and description value using intent
        intentForForecastDetailsActivity.putExtra("key_temp", forecastItem.temperature)
        intentForForecastDetailsActivity.putExtra("key_description", forecastItem.description)

        startActivity(intentForForecastDetailsActivity)
    }

    //For adding a drop down Option Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater =
            menuInflater //<- This is Kotlin's idiomatic way to access getMenuInflater()
        inflater.inflate(R.menu.setting_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.displayUnitItemId -> {
                showDisplaySettingDialog(this, tempDisplaySettingManager)
                true //This will show that we have handled the click
            }
            else -> onOptionsItemSelected(item)
        }
    }


    //region lifecycle's other methods
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "In MainActivity: onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "In MainActivity: onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "In MainActivity: onPause")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("weatherLogCat", "Hello there")
        super.onSaveInstanceState(outState)
        Log.d(TAG, "In MainActivity: onSaveInstanceState")

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "MainActivity : onRestoreInstanceState() ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "In MainActivity: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "In MainActivity: onDestroy")

    }
    //endregion lifecycle's other methods.
}