package com.aks.waetherappgoober

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DailyForecastViewHolder(
    view: View,
    private val tempDisplaySettingManager: TempDisplaySettingManager
) :
    RecyclerView.ViewHolder(view) {
    //layout for representing view items has been created before implementing this method.
    // Now we have to bind individual data coming from our data model with textViews in
    //item_daily_forecast layout file. ViewHolder helps us in that.


    //finding ids for textViews from item_daily_forecast
    //Note: in activity we have context so we could use findByViewId directly,
    // here we have to access the method using view <--- know more????????
    private val tempTextId: TextView = view.findViewById(R.id.tempTextId)
    private val descriptionTextId: TextView = view.findViewById(R.id.descriptionTextId)


    //this method is called from Adapter, DailyForecastAdapter. This method helps us bind individual data Item
    //coming from repository and data model with the views, textViews here, in the layout file,item_daily_forecast.
    fun bind(dailyForecastItem: DailyForecast) {
        tempTextId.text = formatTempForDisplay(
            dailyForecastItem.temperature,
            tempDisplaySettingManager.retrieveSetting()
        )
        descriptionTextId.text = dailyForecastItem.description
    }

}

//                                                                                  _> this constructor of ListAdapter needs an instance of ItemCallback
//                                                                                 |
class DailyForecastAdapter(
    private val tempDisplaySettingManager: TempDisplaySettingManager,
    private var clickHandler: (DailyForecast) -> Unit

) : ListAdapter<DailyForecast, DailyForecastViewHolder>(DIFF_CONFIG) {

    //As we need a ItemCallback in ListAdapter constructor. So using a companion object we are creating an ItemCallback
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


    //member methods in RecyclerView.Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast, parent, false)
        return DailyForecastViewHolder(itemView, tempDisplaySettingManager)
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        //through dailyForecastAdapter.submitList(it) in Observer anonymous class using 'it' we are passing a List<DailyForecast>
        //position traverse through the list which start with index 0.
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
    }
}