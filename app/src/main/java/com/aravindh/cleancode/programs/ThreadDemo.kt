package com.aravindh.cleancode.programs

class ThreadDemo : Runnable{

    override fun run() {
        println("Thread running...")
    }
}

fun main() {
    val threadDemo = ThreadDemo()
    val thread = Thread(threadDemo)

    thread.start()

}