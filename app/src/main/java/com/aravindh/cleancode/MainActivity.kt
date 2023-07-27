package com.aravindh.cleancode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aravindh.cleancode.databinding.ActivityMainBinding
import com.aravindh.cleancode.programs.NetworkRequestDelegateImpl
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val networkRequestDelegate = NetworkRequestDelegateImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        networkRequestDelegate.performNetworkRequest("",
            onSuccess = { success ->
                println("onSuccess: $success")
            }, onError = { error ->
                println("onError: $error")
            })




    }

}