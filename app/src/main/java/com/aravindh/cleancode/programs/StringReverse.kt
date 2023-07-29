package com.aravindh.cleancode.programs

fun main() {

    val input = "MadamMAmaj"

    val reverse = input.reversed()

    if (input.uppercase() == reverse.uppercase()) {
        println("This is Palindrome")
    } else {
        println("This is not Palindrome")
    }
}

//var i = 1
var max = 10

fun printNumbers(i : Int = 1) : Int {
    print("$i ")
    return if(i == max) max else printNumbers(i+1)
}