package com.playground.kotlin.coroutines

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main(args: Array<String>) {
    println(App().greeting)

    println("Running in thread ${Thread.currentThread().name}")
}
