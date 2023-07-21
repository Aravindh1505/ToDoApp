package com.aravindh.cleancode.programs

fun main() {

    val input = "aabbccddeffggg"
    val map = HashMap<Char, Int>()

    for (element in input) {
        if (map.containsKey(element)) {
            map[element] = map[element]!!.plus(1)
        } else {
            map[element] = 1
        }
    }

    for ((key, value) in map) {
        print("$key$value")
    }

}