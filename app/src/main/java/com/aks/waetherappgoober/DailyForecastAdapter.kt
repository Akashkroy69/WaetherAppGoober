package com.aks.waetherappgoober

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DailyForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}

//                                                                                 _> this constructor needs an instance of ItemCallback
//                                                                                |
class DailyForecastAdapter() : ListAdapter<DailyForecast, DailyForecastViewHolder>(DIFF_CONFIG) {

    //As we need a ItemCallback in ListAdapter constructor. So using a companion object we
    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<DailyForecast>() {
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                // in Kotlin === is used to see whether both the references are referring to the exact same instance or not?
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: DailyForecast,
                newItem: DailyForecast
            ): Boolean {
                // in kotlin == compare the content inside the instance, ex ; whether inside the two instances value of
                // temp variable is same or not.
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}