package com.aravindh.cleancode.programs


fun main() {

}

data class Person(val name: String?, val age: Int?)

fun Person.toMap(): Map<String?, Any?> {
    return mapOf("name" to this.name, "age" to this.age)
}

fun Person.greetings() = "Welcome ${this.name}"

fun Person.greet(reverse: (String?) -> Unit) {
    reverse(this.name?.reversed())
}