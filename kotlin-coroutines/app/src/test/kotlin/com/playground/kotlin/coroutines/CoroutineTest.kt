package com.playground.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class CoroutineTest {

    suspend fun hello() {
        delay(1_000)
        println("Hello World")
    }

    @Test fun testCoroutine() {
        GlobalScope.launch {
            hello()
        }
        println("WAITING...")
        runBlocking {
            delay(2_000)
        }
        println("FINISH")
    }

}