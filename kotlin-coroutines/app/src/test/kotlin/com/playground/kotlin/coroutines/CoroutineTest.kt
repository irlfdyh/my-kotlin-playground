package com.playground.kotlin.coroutines

import kotlinx.coroutines.*
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

    @Test
    fun testParentChild() {
        runBlocking {
            val job = GlobalScope.launch {
                launch {
                    delay(2000)
                    println("Child 1 done")
                }
                launch {
                    delay(4000)
                    println("Child 2 done")
                }
                delay(1000)
                println("Parent done")
            }
            job.join()
        }
    }

    @Test
    fun testParentChildCancel() {
        runBlocking {
            val job = GlobalScope.launch {
                launch {
                    delay(2000)
                    println("Child 1 done")
                }
                launch {
                    delay(4000)
                    println("Child 2 done")
                }
                delay(1000)
                println("Parent done")
            }
            job.cancelChildren()
            job.join()
        }
    }

}