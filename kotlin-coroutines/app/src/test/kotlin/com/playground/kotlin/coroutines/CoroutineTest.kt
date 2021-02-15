package com.playground.kotlin.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.concurrent.thread

class CoroutineTest {

    private suspend fun hello() {
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

    @Test
    fun testThread() {
        repeat(1000) {
            thread {
                Thread.sleep(1_000)
                println("Done $it : ${Date()}")
            }
        }
        println("WAITING...")
        Thread.sleep(10_000)
        println("FINISH")
    }

    @Test
    fun testBulkCoroutine() {
        repeat(100_000) {
            GlobalScope.launch {
                delay(1_000)
                println("Done $it : ${Date()} : ${Thread.currentThread().name}")
            }
        }
        println("WAITING...")
        runBlocking {
            delay(3_000)
        }
        println("FINISH")
    }

}