package com.aks.waetherappgoober.location

import com.aks.waetherappgoober.navigator.AppNavigator
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.aks.waetherappgoober.R

class LocationEntryFragment : Fragment() {
    //setting up the com.aks.waetherappgoober.navigator.AppNavigator interface
    private lateinit var appNavigator: AppNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location_entry, container, false)
        //Finding ids for necessary view fields from activity_main.xml
        val enterZipCodeField: EditText = view.findViewById(R.id.zipCodeId)
        val howIsWeatherButtonId: Button = view.findViewById(R.id.howIsWeatherButtonId)


        //Setting up On-Click Listener for the button
        howIsWeatherButtonId.setOnClickListener {
            val zipCode = enterZipCodeField.text.toString()
            if (zipCode.length == 6) {
                appNavigator.navigateToForecastFragmentUsingRV(zipCode)
            }
            else Toast.makeText(
                requireContext(),
                R.string.zipcode_error_message,
                Toast.LENGTH_SHORT
            ).show()
        }


        return view
    }
}