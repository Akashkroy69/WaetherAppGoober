package com.aks.waetherappgoober

import com.aks.waetherappgoober.navigator.AppNavigator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.aks.waetherappgoober.forecast.ForecastFragmentUsingRV
import com.aks.waetherappgoober.location.LocationEntryFragment

class MainActivity : AppCompatActivity(), AppNavigator {
    val TAG = "weatherLogCat"

   // var forecastRepository = ForecastRepository()

    //A reference for TempDisplaySettingManager
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    //region set up methods
    override fun onCreate(savedInstanceState: Bundle?) {

        //Setting up for inflating a layout.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Instantiating tempDisplaySettingManager to be used in the adapter and ViewHolder
        tempDisplaySettingManager = TempDisplaySettingManager(this)


        Log.d(TAG, "In MainActivity: onCreate")




        supportFragmentManager
            .beginTransaction()
            .add(R.id.containerForFragmentsOnMainActivityId, LocationEntryFragment())
            .commit()
    }


    override fun navigateToCurrentForecast(zipcode: String) {
        //forecastRepository.loadForecast(zipcode)
        supportFragmentManager
            .beginTransaction()
            //replace() needs container ID and an instance of Fragment which has to be glued in the container.
            // ForecastFragmentUsingRV.setUpZipcodeInBundleANDargument(zipcode) takes the zipcode which has
            // been sent by LocationEntryFragment() 
            .replace(R.id.containerForFragmentsOnMainActivityId, ForecastFragmentUsingRV.setUpZipcodeInBundleANDargument(zipcode))
            .commit()
    }

    override fun navigateToLocationEntryFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerForFragmentsOnMainActivityId, LocationEntryFragment())
            .commit()
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