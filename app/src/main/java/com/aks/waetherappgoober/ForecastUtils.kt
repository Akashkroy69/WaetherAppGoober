package com.aks.waetherappgoober

fun formatTempForDisplay(temp: Float): String {
    return String.format("%.2f°", temp)
}