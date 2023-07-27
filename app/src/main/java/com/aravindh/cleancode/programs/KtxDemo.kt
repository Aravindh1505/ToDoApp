package com.aravindh.cleancode.programs


fun main() {

    val person = Person(name = "Aravindh", age = 32).toMap()

    println(person)
}

data class Person(val name: String?, val age: Int?)

fun Person.toMap(): Map<String?, Any?> {
    return mapOf("name" to this.name, "age" to this.age)
}