package com.aravindh.cleancode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aravindh.cleancode.databinding.ActivityMainBinding
import com.aravindh.cleancode.user.DaggerUserComponent
import com.aravindh.cleancode.user.UserRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val userComponent = DaggerUserComponent
            .builder()
            .firstName("Manimegalai")
            .lastName("Aravindh")
            .build()

        userComponent.inject(this)

        val userDetails = userRepository.getUser()
        val userAddress = userRepository.getAddress()

        println("userDetails: $userDetails")
        println("userAddress: $userAddress")

    }

}