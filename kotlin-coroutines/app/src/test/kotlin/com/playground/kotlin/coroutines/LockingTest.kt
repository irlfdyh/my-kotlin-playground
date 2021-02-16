package com.playground.kotlin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors

class LockingTest {

    // this is race condition.
    @Test
    fun testRaceCondition() {
        var counter = 0
        val dispatcher = Executors.newFixedThreadPool(10).asCoroutineDispatcher()
        val scope = CoroutineScope(dispatcher)

        repeat(100) {
            scope.launch {
                repeat(1000) {
                    counter++
                }
            }
        }

        runBlocking {
            delay(5000)
            println("total counter: $counter")
        }
    }

    @Test
    fun testMutex() {
        var counter = 0
        val dispatcher = Executors.newFixedThreadPool(10).asCoroutineDispatcher()
        val scope = CoroutineScope(dispatcher)
        val mutex = Mutex()

        repeat(100) {
            scope.launch {
                repeat(1000) {
                    mutex.withLock {
                        counter++
                    }
                }
            }
        }

        runBlocking {
            delay(5000)
            println("total counter: $counter")
        }
    }

    @Test
    fun testSemaphore() {
        var counter = 0
        val dispatcher = Executors.newFixedThreadPool(10).asCoroutineDispatcher()
        val scope = CoroutineScope(dispatcher)
        val semaphore = Semaphore(1)

        repeat(100) {
            scope.launch {
                repeat(1000) {
                    semaphore.withPermit {
                        counter++
                    }
                }
            }
        }

        runBlocking {
            delay(5000)
            println("total counter: $counter")
        }
    }

}