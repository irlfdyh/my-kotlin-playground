package com.playground.kotlin.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors

class CoroutineScopeTest {

    @Test
    fun testScope() {
        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            delay(1000)
            println("Run ${Thread.currentThread().name}")
        }

        scope.launch {
            delay(1000)
            println("Run ${Thread.currentThread().name}")
        }

        runBlocking {
            delay(2000)
            println("Done ${Thread.currentThread().name}")
        }
    }

    @Test
    fun testCancelScope() {
        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            delay(2000)
            println("Run ${Thread.currentThread().name}")
        }

        scope.launch {
            delay(2000)
            println("Run ${Thread.currentThread().name}")
        }

        runBlocking {
            delay(1000)
            scope.cancel()
            delay(2000)
            println("Done ${Thread.currentThread().name}")
        }
    }

    private suspend fun getFoo(): Int {
        println("Foo ${Thread.currentThread().name}")
        delay(1000)
        return 10
    }

    private suspend fun getBar(): Int {
        println("Bar ${Thread.currentThread().name}")
        delay(1000)
        return 10
    }

    private suspend fun getSum() = coroutineScope {
        println("Sum ${Thread.currentThread().name}")
        val foo = async { getFoo() }
        val bar = async { getBar() }
        foo.await() + bar.await()
    }

    @Test
    fun testCoroutineScopeFunction() {
        val scope = CoroutineScope(Dispatchers.Default)
        val job = scope.launch {
            println("Caller: ${Thread.currentThread().name}")
            val result = getSum()
            println(result)
        }
        runBlocking {
            job.join()
        }
    }

    @Test
    fun testParentChildDispatcher() {
        val dispatcher = Executors.newFixedThreadPool(10).asCoroutineDispatcher()
        val scope = CoroutineScope(dispatcher)

        val job = scope.launch {
            println("Parent Scope : ${Thread.currentThread().name}")
            coroutineScope {
                launch {
                    println("Child Scope : ${Thread.currentThread().name}")
                }
            }
        }

        runBlocking {
            job.join()
        }

    }

    @Test
    fun testParentChildCancel() {
        val dispatcher = Executors.newFixedThreadPool(10).asCoroutineDispatcher()
        val scope = CoroutineScope(dispatcher)

        val job = scope.launch {
            println("Parent Scope : ${Thread.currentThread().name}")
            coroutineScope {
                launch {
                    delay(2000)
                    println("Child Scope : ${Thread.currentThread().name}")
                }
            }
        }

        runBlocking {
            job.cancelAndJoin()
        }

    }

}