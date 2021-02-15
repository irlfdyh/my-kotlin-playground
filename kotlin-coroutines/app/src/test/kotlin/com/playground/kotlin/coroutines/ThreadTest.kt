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

    @Test
    fun testMultipleThread() {
        // Using old java Runnable
        val thread1 = Thread(Runnable {
            println(Date())
            Thread.sleep(2_000)
            println("Finish Thread 1 : ${Thread.currentThread().name} : ${Date()}")
        })

        val thread2 = Thread(Runnable {
            println(Date())
            Thread.sleep(2_000)
            println("Finish Thread 2 : ${Thread.currentThread().name} : ${Date()}")
        })

        thread1.start()
        thread2.start()

        println("Waiting process to finish")
        Thread.sleep(3_000)
        println("Finished")
    }
}