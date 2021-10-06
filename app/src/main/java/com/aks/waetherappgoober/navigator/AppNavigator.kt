package com.aks.waetherappgoober.navigator

interface AppNavigator {
    fun navigateToForecastFragmentUsingRV(zipcode : String)
    fun navigateToLocationEntryFragment()
}