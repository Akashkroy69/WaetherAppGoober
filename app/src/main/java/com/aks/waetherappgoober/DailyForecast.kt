package com.aks.waetherappgoober
//This is a data class for modelling our data coming from repository which either loads data from network or local database.
data class DailyForecast(
    val temperature: Float,
    val description: String
)