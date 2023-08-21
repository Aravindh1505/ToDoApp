package com.aravindh.cleancode.user

import javax.inject.Inject

class UserRepository @Inject constructor (private val user: User) {


    fun getUser(): String = user.getUserDetails()

    fun getAddress() = user.getAddress()
}