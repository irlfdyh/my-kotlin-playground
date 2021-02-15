package com.playground.kotlin.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

class JobTest {

    @Test
    fun testJob() {
        runBlocking {
            GlobalScope.launch {
                delay(2_000)
                println("Coroutine Done : ${Thread.currentThread().name}")
            }
        }
    }

    @Test
    fun testLazyJob() {
        runBlocking {
            val job: Job = GlobalScope.launch(start = CoroutineStart.LAZY) {
                delay(2_000)
                println("Coroutine Done : ${Thread.currentThread().name}")
            }
            job.start()
            delay(3_000)
        }
    }

    @Test
    fun testJoinJob() {
        runBlocking {
            val job: Job = GlobalScope.launch() {
                delay(2_000)
                println("Coroutine Done : ${Thread.currentThread().name}")
            }
            job.join()
        }
    }

    @Test
    fun testCancelJob() {
        runBlocking {
            val job: Job = GlobalScope.launch() {
                delay(2_000)
                println("Coroutine Done : ${Thread.currentThread().name}")
            }
            job.cancel()
            delay(3_000)
        }
    }

    @Test
    fun testJobJoinAll() {
        runBlocking {
            val job1 = GlobalScope.launch {
                delay(2_000)
                println("Coroutine Done : ${Thread.currentThread().name}")
            }
            val job2 = GlobalScope.launch {
                delay(2_000)
                println("Coroutine Done : ${Thread.currentThread().name}")
            }
            joinAll(job1, job2)
        }
    }

    @Test
    fun testCancelFinally() {
        runBlocking {
            val job: Job = GlobalScope.launch() {
                try {
                    println("Coroutine Started : ${Thread.currentThread().name}")
                    delay(2_000)
                    println("Coroutine Done : ${Thread.currentThread().name}")
                } finally {
                    println("FINISH")
                }
            }
            job.cancelAndJoin()
        }
    }

}