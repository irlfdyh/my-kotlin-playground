package com.playground.kotlin.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class AsyncTest {

    suspend fun getFoo(): Int {
        delay(1000)
        return 10
    }

    suspend fun getBar(): Int {
        delay(1000)
        return 10
    }

    @Test
    fun testAsync() {
        runBlocking {
            val time = measureTimeMillis {
                val foo = GlobalScope.async { getFoo() }
                val bar: Deferred<Int> = GlobalScope.async { getBar() }

                val result = foo.await() + bar.await()
                println("Result: $result")
            }
            println("Total time: $time")
        }
    }

    @Test
    fun testAwaitAll() {
        runBlocking {
            val time = measureTimeMillis {
                val foo: Deferred<Int> = GlobalScope.async { getFoo() }
                val bar: Deferred<Int> = GlobalScope.async { getBar() }

                val result = awaitAll(foo, bar).sum()
                println("Result: $result")
            }
            println("Total time: $time")
        }
    }

}