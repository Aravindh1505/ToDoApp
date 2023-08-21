package com.aravindh.cleancode.programs

fun main() {

    val add : (Int, Int) -> Int = {x, y -> x * y}
    val output = lambdaDemo(5, 5, add)
    println(output)

}

fun lambdaDemo(x : Int, y : Int, calc: (Int, Int) -> Int) : Int {
    return calc(x , y)
}