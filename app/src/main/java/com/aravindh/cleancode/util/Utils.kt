package com.aravindh.cleancode.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import java.text.SimpleDateFormat
import java.util.Date

object Utils {

    fun popBackStack(view: View) {
        Navigation.findNavController(view).popBackStack()
    }

    fun showToast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun formatDateAndTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
        val date = Date(timestamp)
        return sdf.format(date)
    }
}