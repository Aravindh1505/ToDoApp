package com.aravindh.cleancode.programs


fun main() {

    val networkRequestDelegateImpl = NetworkRequestDelegateImpl()

    networkRequestDelegateImpl.performNetworkRequest("",
        onSuccess = {
            println(it)
        }, onError = {
            println(it)
        })

}

interface NetworkRequestDelegate {

    fun performNetworkRequest(url: String?, onSuccess: (String) -> Unit, onError: (String) -> Unit)

    fun performGetRequest()
}

class NetworkRequestDelegateImpl : NetworkRequestDelegate {
    override fun performNetworkRequest(url: String?, onSuccess: (String) -> Unit, onError: (String) -> Unit) {

        val isSuccessful = false

        if (isSuccessful) {
            onSuccess("The network is Success")
        } else {
            onError("The network call is Error")
        }

    }

    override fun performGetRequest() {

    }

}