package com.aks.waetherappgoober

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun formatTempForDisplay(temp: Float, tempDisplaySetting: TempDisplaySetting): String {
    return when (tempDisplaySetting) {
        TempDisplaySetting.Fahrenheit -> String.format("%.2f °F", temp)
        TempDisplaySetting.Celsius -> {
            val tempAsCelsius = (temp - 32) * (5f / 9f)
            String.format("%.2f °C", tempAsCelsius)
        }
    }
    // return String.format("%.2f°", temp)
}

//for building a dialog which will be called from onOptionsItemSelected
fun showDisplaySettingDialog(context: Context, tempDisplaySettingManager: TempDisplaySettingManager) {
    val dialogBuilder = AlertDialog.Builder(context)
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
                context,
                "Setting change will come in action after app restart.",
                Toast.LENGTH_SHORT

            ).show()
        }
    dialogBuilder.show()
}