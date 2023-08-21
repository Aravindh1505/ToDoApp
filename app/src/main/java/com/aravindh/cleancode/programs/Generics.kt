package com.aravindh.cleancode.programs


fun main() {

    val listNumber = listOf(4, 7, 2, 1, 9, 8)

    val finder = Finder(listNumber)
    finder.findItem(4) {
        println("output: $it")
    }

}

class Finder<T>(private val list: List<T>) {

    fun findItem(element: T, find: (result: T?) -> Unit) {
        val findValueInList = list.filter {
            it == element
        }

        if (findValueInList.isEmpty()) return find(null) else find(element)
    }

}