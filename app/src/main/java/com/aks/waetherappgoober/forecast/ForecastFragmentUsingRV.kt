package com.aks.waetherappgoober.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aks.waetherappgoober.*
import com.aks.waetherappgoober.details.ForecastDetailsActivity
import com.aks.waetherappgoober.location.LocationEntryFragment
import com.aks.waetherappgoober.navigator.AppNavigator
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ForecastFragmentUsingRV() : Fragment() {

    private lateinit var appNavigator: AppNavigator

    //a reference for the forecast Repository.
    private val forecastRepository = ForecastRepository()

    //A reference for TempDisplaySettingManager
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager


    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val zipcode = arguments?.getString(KEY_ZIPCODE)

        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forecast_using_r_v, container, false)


        //setting up Recycler view
        // For setting up recycler view we need to: 1. create a reference for RV, 2. set up LayOut Manger
        // 3. Set up RV Adapter-> For Adapter we need to set up : 1. Adapter, 2. ViewHolder 3. Item callback

        //1. Creating a reference for recycler view.
        val forecastItemsRVId: RecyclerView = view.findViewById(R.id.forecastItemRVId)
        //2.setting up RV's layoutManager Property as Linear Layout Manager
        forecastItemsRVId.layoutManager = LinearLayoutManager(requireContext())
        //3. Adapter. We created and set up the system for Adapter and ViewHolder which is needed by the RecyclerView
        //see implementation for adapter and ViewHolder in DailyForecastAdapter() class.
        //A ref for the Adapter class
        val dailyForecastAdapter = DailyForecastAdapter(tempDisplaySettingManager) { forecastItem ->
            val message = getString(
                R.string.string_format_for_toasting_values_fromForecastItem,
                forecastItem.temperature,
                forecastItem.description
            )
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

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
        forecastRepository.weeklyForecast.observe(viewLifecycleOwner, weeklyForecastObserver)

        if (zipcode != null) {
            forecastRepository.loadForecast(zipcode)
        }

        //
        val buttonForTransitingToLocationEntryButtonId =
            view.findViewById<FloatingActionButton>(R.id.buttonForTransitingToLocationEntryButtonId)
        buttonForTransitingToLocationEntryButtonId.setOnClickListener {
            appNavigator.navigateToLocationEntryFragment()
        }

        return view
    }

    //method for navigating to a new Activity using an intent.
    private fun passForecastDetailsUsingIntent(forecastItem: DailyForecast) {
        val intentForForecastDetailsActivity =
            Intent(requireContext(), ForecastDetailsActivity::class.java)

        //for attaching extra values and data with the intent which is being used to invoke an Activity, here ForecastDetailsActivity
        //here we are going to pass temperature and description value using intent
        intentForForecastDetailsActivity.putExtra("key_temp", forecastItem.temperature)
        intentForForecastDetailsActivity.putExtra("key_description", forecastItem.description)

        startActivity(intentForForecastDetailsActivity)
    }

    companion object {
        private const val KEY_ZIPCODE = "key_zipcode"

        fun setUpZipcodeInBundleANDargument(zipcode: String): ForecastFragmentUsingRV {
            val fragmentUsingRV = ForecastFragmentUsingRV()

            val args = Bundle()

            args.putString(KEY_ZIPCODE, zipcode)

            //Read the hint-documentation. This property invokes the setArgument() for setting up the arguments for the
            // constructor which can be accessed
            //
            fragmentUsingRV.arguments = args

            return fragmentUsingRV
        }
    }
}