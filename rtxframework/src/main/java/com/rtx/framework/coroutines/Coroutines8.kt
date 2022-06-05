package com.rtx.framework.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * Composing suspending functions
 * The use-case for async(start = CoroutineStart.LAZY) is a replacement for
 * the standard lazy function in cases when computation of the value involves suspending functions.
 */
fun main() {
    //ex25()
    //ex26()
    //ex27()
    ex28()
}

suspend fun doSomethingUsefulOne(): Int {
    println("fun1 started.")
    delay(1000L) // pretend we are doing something useful here
    println("fun1 ended.")
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    println("fun2 started.")
    delay(1000L) // pretend we are doing something useful here, too
    println("fun2 ended.")
    return 29
}

/**
 * Because the code in the coroutine, just like in the regular code, is sequential by default
 */
fun ex25() = runBlocking {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms") // 2017 ms
}

/**
 * Concurrent using async
 */
fun ex26() = runBlocking {
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms") // 1030 ms
}

/**
 * Concurrent using lazy started async (sequential)
 */
fun ex27() = runBlocking {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms") // 2027 ms
}

/**
 * Concurrent using lazy started async (explicit start() call makes them start.)
 */
fun ex28() = runBlocking {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        one.start()
        two.start()
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms") // 1028 ms
}