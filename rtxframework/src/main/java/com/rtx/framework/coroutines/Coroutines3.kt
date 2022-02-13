package com.rtx.framework.coroutines

import kotlinx.coroutines.*

/**
 * Coroutine scope is responsible for the structure and parent-child relationships between different coroutines.
 *
 * Coroutine context stores additional technical information used to run a given coroutine,
 * like the coroutine custom name, or the dispatcher specifying the threads the coroutine should be scheduled on.
 *
 * launch, async, or runBlocking are used to start a new coroutine.
 *
 * the implicit receiver type is the CoroutineScope:
 * launch { /* this: CoroutineScope */ }
 *
 * New coroutines can only be started inside a scope.
 *
 * launch and async are declared as extensions to CoroutineScope.
 * runBlocking is defined as a top-level function and
 * it is intended primarily to be used in main functions and tests as a bridge function
 *
 * coroutines started inside a suspend function,
 * automatically becomes a child of the outer scope that this suspend function is called from.
 * This is called "structured concurrency".
 *
 * top-level "independent" coroutines
 * it's also possible to start coroutines from globa scope with
 * GlobalScope.async or GlobalScope.launch
 *
 * structured vs global scope
 *
 * The scope is generally responsible for child coroutines,
 * and their lifetime is attached to the lifetime of the scope.
 * The scope can automatically cancel child coroutines if something goes wrong.
 * The scope automatically waits for completion of all the child coroutines.
 *
 *  The coroutines started from the global scope are all independent;
 *  their lifetime is limited only by the lifetime of the whole application.
 *  can be cancelled explicitly, but it won't happen automatically.
 *
 *  With structured concurrency,
 *  we only need to cancel the parent coroutine and
 *  this automatically propagates cancellation to all the child coroutines.
 *
 * With structured concurrency we can specify the major context elements (like dispatcher) once,
 * when we create a top-level coroutine. All the nested coroutines then
 * inherit the context and modify it only if needed.
 *
 * Note that when we write code with coroutines for UI applications,
 * e.g., for Android, it is common practice to use CoroutineDispatchers.Main by default
 * for the top coroutine and then
 * to explicitly put a different dispatcher when we need to run the code on a different thread.
 *
 */
fun main() {
    ex11()
    ex12()
}

fun ex11() = runBlocking { // parent
    launch { // child
        println("Text")
    }
}

fun ex12() = runBlocking {
    launch(Dispatchers.Default) {  // outer scope
        val str = doThings12()
        println(str)
    }
}

suspend fun doThings12() = coroutineScope {
    // this scope inherits the context from the outer scope
    val d = async {   // nested coroutine started with the inherited context
        delay(1000L)
        "Reha"
    }
    d.await()
}
