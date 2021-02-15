package com.playground.kotlin.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

class ExceptionHandlingTest {

    @Test
    fun testExceptionLaunch() {
        runBlocking {
            val job = GlobalScope.launch {
                println("Start coroutine")
                throw IllegalArgumentException()
            }
            job.join()
            println("Finish")
        }
    }

    @Test
    fun testExceptionAsync() {
        runBlocking {
            val deferred = GlobalScope.async {
                println("Start Coroutine")
                throw IllegalArgumentException()
            }

            try {
             deferred.await()
            } catch (error: IllegalArgumentException) {
                println("Error")
            } finally {
                println("Finish")
            }
        }
    }

    @Test
    fun testExceptionHandler() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            println("Ups error ${throwable.message}")
        }

        val scope = CoroutineScope(Dispatchers.IO + exceptionHandler)

        runBlocking {
            val job1 = GlobalScope.launch(exceptionHandler) {
                println("Start coroutine")
                throw IllegalArgumentException("error message")
            }
            val job2 = scope.launch(exceptionHandler) {
                println("Start coroutine")
                throw IllegalArgumentException("error message")
            }
            joinAll(job1, job2)
            println("Finish")
        }
    }

}