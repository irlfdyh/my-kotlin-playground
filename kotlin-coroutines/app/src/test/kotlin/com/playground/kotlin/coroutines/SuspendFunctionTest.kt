package com.playground.kotlin.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.*

class SuspendFunctionTest {

    private suspend fun helloWorld() {
        println("hello: ${Date()} : ${Thread.currentThread().name}")
        delay(2_000)
        println("world: ${Date()} : ${Thread.currentThread().name}")
    }

    @Test
    fun testSuspendFunction() {
        runBlocking {
            helloWorld()
        }
    }

}