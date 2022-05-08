package com.rtx.framework.coroutines

import kotlinx.coroutines.*

/**
 * The most obvious practical reason to cancel execution of a coroutine is
 * because its execution time has exceeded some timeout.
 */

fun main() {
    ex20()
    ex21()
    ex22()
}

/**
 * Exception in thread "main" kotlinx.coroutines.TimeoutCancellationException
 * we have used withTimeout right inside the main function
 */
fun ex20() = runBlocking {
    withTimeout(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
}

/**
 * try {...} catch (e: TimeoutCancellationException) {...} or withTimeoutOrNull() { }
 */
fun ex21() = runBlocking {
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
        "Done" // will get cancelled before it produces this result
    }
    println("Result is $result")
}

/**
 * in launch { } block no exception thrown.
 * That is because inside a cancelled coroutine CancellationException is
 * considered to be a normal reason for coroutine completion.
 */
fun ex22() = runBlocking {
    launch {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
    }
}
