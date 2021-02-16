package com.playground.kotlin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select
import org.junit.jupiter.api.Test

class SelectTest {

    @Test
    fun testSelectDeferred() {
        val scope = CoroutineScope(Dispatchers.IO)

        val deferred1 = scope.async {
            delay(1000)
            1000
        }

        val deferred2 = scope.async {
            delay(2000)
            2000
        }

        val job = scope.launch {
            val win = select<Int> {
                deferred1.onAwait { it }
                deferred2.onAwait { it }
            }
            println("Win: $win")
        }

        runBlocking {
            job.join()
        }
    }

    @Test
    fun testSelectChannel() {
        val scope = CoroutineScope(Dispatchers.IO)

        val receiveChannel1 = scope.produce {
            delay(1000)
            send(1000)
        }

        val receiveChannel2 = scope.produce {
            delay(2000)
            send(2000)
        }

        val job = scope.launch {
            val win = select<Int> {
                receiveChannel1.onReceive { it }
                receiveChannel2.onReceive { it }
            }
            println("Win: $win")
        }

        runBlocking {
            job.join()
        }
    }

    @Test
    fun testSelectChannelAndDeferred() {
        val scope = CoroutineScope(Dispatchers.IO)

        val receiveChannel1 = scope.produce {
            delay(1000)
            send(1000)
        }

        val receiveDeferred2 = scope.async {
            delay(2000)
            2000
        }

        val job = scope.launch {
            val win = select<Int> {
                receiveChannel1.onReceive { it }
                receiveDeferred2.onAwait { it }
            }
            println("Win: $win")
        }

        runBlocking {
            job.join()
        }
    }

}