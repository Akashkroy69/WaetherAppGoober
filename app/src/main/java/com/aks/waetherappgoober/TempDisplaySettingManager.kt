package com.aks.waetherappgoober

import android.content.Context
import androidx.core.content.edit

enum class TempDisplaySetting {
    Fahrenheit, Celsius
}

class TempDisplaySettingManager(context: Context) {
    val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    //A method using which the this file can be edited or we can say that using which we can add some
    //data in the file.
    fun updateSetting(setting: TempDisplaySetting) {
        preferences.edit().putString("key_temp_display_unit", setting.name).commit()
    }

    // To retrieve setting
    fun retrieveSetting(): TempDisplaySetting {
        val settingValue =
            preferences.getString("key_temp_display_unit", TempDisplaySetting.Fahrenheit.name)
                ?: TempDisplaySetting.Fahrenheit.name

        return TempDisplaySetting.valueOf(settingValue) //valueOf is method for enum class. We are passing a string
        //value in it. It basically matches the constants in the enum class. If that matches to any value, It returns
        //the enum constant.
    }
}
