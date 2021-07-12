package com.aks.waetherappgoober

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val enterZipcodeField: EditText = findViewById(R.id.zipCodeId)
        val howIsWeatherButtonId: Button = findViewById(R.id.howIsWeatherButtonId)

        howIsWeatherButtonId.setOnClickListener {
            val zipcode = enterZipcodeField.text.toString()
            if (zipcode.length == 6) Toast.makeText(this, zipcode, Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, "Please enter a valid Zip Code !!", Toast.LENGTH_SHORT).show()
        }
    }
}