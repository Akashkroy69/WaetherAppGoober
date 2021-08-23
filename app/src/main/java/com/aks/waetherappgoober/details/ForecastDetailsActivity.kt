package com.aks.waetherappgoober.details

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.aks.waetherappgoober.R
import com.aks.waetherappgoober.TempDisplaySetting
import com.aks.waetherappgoober.TempDisplaySettingManager
import com.aks.waetherappgoober.formatTempForDisplay

class ForecastDetailsActivity : AppCompatActivity() {
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager


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

        //instantiating an instance for TempDisplaySettingManager
        tempDisplaySettingManager = TempDisplaySettingManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater =
            menuInflater //<- This is Kotlin's idiomatic way to access getMenuInflater()
        inflater.inflate(R.menu.setting_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.displayUnitItemId -> {
                showDisplaySettingDialog()
                true //This will show that we have handled the click
            }
            else -> onOptionsItemSelected(item)
        }
    }

    //for building a dialog
    private fun showDisplaySettingDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle("Choose a Display Unit")
            .setMessage("What kind of display Unit you will prefer: ")

            //Tips: Do not convert this in to lambda. I have left this way to understand what is going on under the hood.
            .setPositiveButton("F°", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenheit)
                }

            })
            //We can write a lambda in place of the second argument written in setPositiveButton as following
            .setNeutralButton("C°") { dialog, which ->
                tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celsius)

            }
            .setOnDismissListener {
                Toast.makeText(
                    this,
                    "Setting change will come in action after app restart.",
                    Toast.LENGTH_SHORT

                ).show()
            }
        dialogBuilder.show()
    }
}