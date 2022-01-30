package com.rtx.framework.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * a coroutine is an instance of suspendable computation.
 * a block of code to run that works concurrently with the rest of the code.
 *
 * a coroutine is not bound to any particular thread.
 * It may suspend its execution in one thread and resume in another one.
 *
 * runBlocking = coroutine builder, bridges between
 * the non-coroutine world of a regular and
 * the code with coroutines inside of its block.
 *
 * the thread that runs it (in this case — the main thread) gets blocked for the duration of the call,
 * until all the coroutines inside runBlocking { ... } complete their execution
 *
 * launch = coroutine builder, declared only in the CoroutineScope
 *
 * delay = special suspending function
 *
 * Coroutines follow a principle of structured concurrency which means that
 * new coroutines can be only launched in a specific CoroutineScope which
 * delimits the lifetime of the coroutine
 *
 * This ensures that they are not lost and do not leak.
 * An outer scope cannot complete until all its children coroutines complete
 *
 * runBlocking is a regular function and coroutineScope is a suspending function.
 */
fun main() {
    ex1()
    ex2()
    ex3()
    ex4()
    ex5()
    ex6()
}

fun ex1() = runBlocking {
    launch { // launch a new coroutine and continue
        delay(1000L)
        println("World!")
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}

fun ex2() = runBlocking {
    launch { doHello() }
    println("Hello")
}

/**
 * Suspending functions can be used inside coroutines just like regular functions.
 *
 * use other suspending functions (like delay) as well.
 */
suspend fun doHello() {
    delay(1000L)
    println("World!")
}

fun ex3() = runBlocking {
    doHello2()
}

/**
 * Our own defined coroutine scope builder.
 *
 * It creates a coroutine scope and does not complete until all launched children complete.
 */
suspend fun doHello2() = coroutineScope {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
}

/**
 * Sequentially executes doHello3() followed by "Done"
 * Output is;
 *  Hello
 *  World 1!
 *  World 2!
 *  Done
 */
fun ex4() = runBlocking {
    doHello3()
    println("Done")
}

/**
 * perform multiple concurrent operations
 *
 * concurrently executes both sections
 */
suspend fun doHello3() = coroutineScope {
    launch {
        delay(2000L)
        println("World 2!")
    }
    launch {
        delay(1000L)
        println("World 1!")
    }
    println("Hello")
}

/**
 * Output is;
 *  Hello
 *  World!
 *  Done
 */
fun ex5() = runBlocking {
    doHello4()
}

/**
 * A launch coroutine builder returns a Job object that is a handle to
 *
 * the launched coroutine and can be used to explicitly wait for its completion
 *
 */
suspend fun doHello4() = coroutineScope {
    val job = launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
    job.join()
    println("Done")
}

/**
 * Coroutines ARE light-weight
 *
 * launches 100K coroutines and, after 5 seconds, each coroutine prints a dot.
 *
 * remove runBlocking, replace launch with thread, and replace delay with Thread.sleep ??
 * Out Of Memory Exception.
 */
fun ex6() = runBlocking {
    repeat(100_000) { // launch a lot of coroutines
        launch {
            delay(5000L)
            print(".")
        }
    }
}
