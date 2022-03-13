package com.rtx.framework.coroutines

import kotlinx.coroutines.*

/**
 * All the suspending functions in kotlinx.coroutines are cancellable.
 *
 * Run non-cancellable block;
 * Any attempt to use a suspending function in the finally block of the previous example causes CancellationException,
 * because the coroutine running this code is cancelled.
 *
 * Usually we have closing operations like closing file,cancelling a job, closing any kind of comm. channel
 * which are usually non-blocking and do not involve any suspend functions.
 * in rare case use withContext(NonCancellable) { } block in finally.
 */

fun main() {
    ex15()
    ex16()
    ex17()
    ex18()
    ex19()
}

/*
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
main: Now I can quit.
 */
fun ex15() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    job.join() // waits for job's completion
    println("main: Now I can quit.")
}

/**
 * However, if a coroutine is working in a computation and does not check for cancellation,
 * then it cannot be cancelled.
 *
 * continues to print "I'm sleeping" even after cancellation
 * until the job completes by itself after ten iterations.
 *
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
job: I'm sleeping 3 ...
job: I'm sleeping 4 ...
job: I'm sleeping 5 ...
job: I'm sleeping 6 ...
job: I'm sleeping 7 ...
job: I'm sleeping 8 ...
job: I'm sleeping 9 ...
main: Now I can quit.
 */
fun ex16() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 10) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

/**
 * Making computation code cancellable
 * 1. The first one is to periodically invoke a suspending function that checks for cancellation = yield()
 * 2. The other one is to explicitly check the cancellation status = isActive()
job: I'm sleeping 0 ...
job: I'm sleeping 1 ...
job: I'm sleeping 2 ...
main: I'm tired of waiting!
main: Now I can quit.
 */
fun ex17() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // cancellable computation loop
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

/**
 * Closing resources with finally.
 */
fun ex18() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("job: I'm running finally")
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

/**
 * Closing resources with finally and NonCancellable.
 */
fun ex19() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("job: I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}