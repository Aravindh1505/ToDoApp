package com.aravindh.cleancode.user

import javax.inject.Inject
import javax.inject.Named

class User @Inject constructor(
    private val address: Address,
    @Named("firstName") private val firstName: String,
    @Named("lastName") private val lastName: String
) {


    fun getUserDetails() = "$firstName $lastName"

    fun getAddress() = address.getUserAddress()
}