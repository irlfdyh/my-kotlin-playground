package com.playground.kotlin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.jupiter.api.Test

class FlowTest {

    @Test
    fun testFlow() {
        val flow1: Flow<Int> = flow {
            println("Start flow")
            repeat(100) {
                println("Emit $it")
                emit(it)
            }
        }
        runBlocking {
            flow1.collect {
                println("Receive $it")
            }
        }
    }

    private suspend fun numberFlow(): Flow<Int> = flow {
        repeat(100) {
            emit(it)
        }
    }

    private suspend fun changeToString(number: Int): String {
        delay(100)
        return "Number $number"
    }

    @Test
    fun testFlowOperator() {
        runBlocking {
            val flow1 = numberFlow()
            flow1.filter { it % 2 == 0 }
                .map { changeToString(it) }
                .collect { println(it) }
        }
    }

    @Test
    fun testFlowException() {
        runBlocking {
            numberFlow()
                .map { check(it < 10); it }
                .onEach { println(it) }
                .catch { println("Error ${it.message}") }
                .onCompletion { println("Done") }
                .collect()
        }
    }

    @Test
    fun testCancellableFlow() {
        val scope = CoroutineScope(Dispatchers.Default)
        runBlocking {
            val job = scope.launch {
                numberFlow()
                    .onEach {
                        if (it > 10) cancel()
                        else println(it)
                    }
                    .collect()
            }
            job.join()
        }
    }

}