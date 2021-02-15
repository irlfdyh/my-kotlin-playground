package com.playground.kotlin.coroutines

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.concurrent.thread

class ThreadTest {

    @Test
    fun testThreadName() {
        val threadName =  Thread.currentThread().name
        println("Running in thread $threadName")
    }

    @Test
    fun testNewThread() {
        // Using old java Runnable
//        val runnable = Runnable {
//            println(Date())
//            Thread.sleep(2_000)
//            println("Finish : ${Date()}")
//        }

//        val thread = Thread(runnable)
//        thread.start()

        // Using kotlin concurrent
        thread(start = true) {
            println(Date())
            Thread.sleep(2_000)
            println("Finish : ${Date()}")
        }

        println("Waiting process to finish")
        Thread.sleep(3_000)
        println("Finished")
    }
}