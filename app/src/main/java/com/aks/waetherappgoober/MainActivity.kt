package com.aks.waetherappgoober

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //region set up methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val enterZipcodeField: EditText = findViewById(R.id.zipCodeId)
        val howIsWeatherButtonId: Button = findViewById(R.id.howIsWeatherButtonId)
        //Setting up On-Click Listener.
        howIsWeatherButtonId.setOnClickListener {
            val zipcode = enterZipcodeField.text.toString()
            if (zipcode.length == 6) Toast.makeText(this, zipcode, Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, R.string.zipcode_error_message, Toast.LENGTH_SHORT).show()
        }
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