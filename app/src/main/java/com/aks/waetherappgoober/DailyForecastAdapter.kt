package com.aks.waetherappgoober

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DailyForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}

//                                                                                 _> this constructor needs an instance of ItemCallback
//                                                                                |
class DailyForecastAdapter() : ListAdapter<DailyForecast, DailyForecastViewHolder>(DIFF_CONFIG) {

    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<DailyForecast>() {
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                // in Kotlin === is used to see whether the two instances, not just content, are exactly same or not?
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
}