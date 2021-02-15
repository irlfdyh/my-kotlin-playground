package com.playground.kotlin.coroutines

import org.junit.jupiter.api.Test
import java.util.*
import java.util.concurrent.Executors

class ExecutorServiceTest {

    @Test
    fun testSingleThreadPool() {
        val executorService = Executors.newSingleThreadExecutor()

        repeat(10) {
            val runnable = Runnable {
                Thread.sleep(1000)
                println("Done $it ${Thread.currentThread().name} ${Date()}")
            }
            executorService.execute(runnable)
            println("INSERT RUNNABLE : $it")
        }

        println("WAITING THREAD TO EXECUTE...")
        Thread.sleep(11_000)
        println("DONE")
    }

    @Test
    fun testFixThreadPool() {
        val executorService = Executors.newFixedThreadPool(3)

        repeat(10) {
            val runnable = Runnable {
                Thread.sleep(1000)
                println("Done $it ${Thread.currentThread().name} ${Date()}")
            }
            executorService.execute(runnable)
            println("INSERT RUNNABLE : $it")
        }

        println("WAITING THREAD TO EXECUTE...")
        Thread.sleep(11_000)
        println("DONE")
    }

    @Test
    fun testCachedThreadPool() {
        val executorService = Executors.newCachedThreadPool()

        repeat(10) {
            val runnable = Runnable {
                Thread.sleep(1000)
                println("Done $it ${Thread.currentThread().name} ${Date()}")
            }
            executorService.execute(runnable)
            println("INSERT RUNNABLE : $it")
        }

        println("WAITING THREAD TO EXECUTE...")
        Thread.sleep(11_000)
        println("DONE")
    }

}