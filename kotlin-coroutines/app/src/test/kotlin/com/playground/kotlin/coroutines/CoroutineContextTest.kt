package com.playground.kotlin.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors

@ExperimentalStdlibApi
class CoroutineContextTest {

    @Test
    fun testCoroutineContext() {
        runBlocking {
            val job = GlobalScope.launch {
                val context = coroutineContext
                println(context)
                println(context[Job])
                println(context[CoroutineDispatcher])
            }
            job.join()
        }
    }

    @Test
    fun testCoroutineName() {
        val scope = CoroutineScope(Dispatchers.IO)
        val job = scope.launch(CoroutineName("parent")) {
            println("Parent run on thread : ${Thread.currentThread().name}")
            withContext(CoroutineName("child")) {
                println("Child run on thread : ${Thread.currentThread().name}")
            }
        }
        runBlocking {
            job.join()
        }
    }

    @Test
    fun testCoroutineNameContext() {
        val scope = CoroutineScope(Dispatchers.IO + CoroutineName("test"))
        val job = scope.launch {
            println("Parent run on thread : ${Thread.currentThread().name}")
            withContext(Dispatchers.IO) {
                println("Child run on thread : ${Thread.currentThread().name}")
            }
        }
        runBlocking {
            job.join()
        }
    }

    @Test
    fun testCoroutineElements() {
        val dispatcher = Executors.newFixedThreadPool(10).asCoroutineDispatcher()
        val scope = CoroutineScope(dispatcher + CoroutineName("test"))
        val job = scope.launch(CoroutineName("parent")) {
            println("Parent run on thread : ${Thread.currentThread().name}")
            withContext(Dispatchers.IO.plus(CoroutineName("child"))) {
                println("Child run on thread : ${Thread.currentThread().name}")
            }
        }
        runBlocking {
            job.join()
        }
    }

}