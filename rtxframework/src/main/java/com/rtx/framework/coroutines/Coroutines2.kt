package com.rtx.framework.coroutines

import kotlinx.coroutines.*

/**
 * coroutine builders: launch, async, runBlocking
 *
 * async starts a new coroutine and returns a Deferred object.
 *
 * Deferred represents a concept known by other names like,
 * Future or Promise
 *
 * it stores a computation, but it defers the moment you get the final result;
 * it promises the result sometime in the future
 *
 * async and launch
 *
 * launch is used for starting a computation that isn't expected to return a specific result.
 * launch returns Job which represents the coroutine.
 * to wait for its completion we can call Job.join()
 *
 * Deferred is a generic type which extends Job.
 * An async call can return a Deferred<Int> or Deferred<CustomType>
 * To get the result of a coroutine, we call await() on the Deferred instance.
 *
 * While waiting for the result, the coroutine that this await is called from,
 * suspends.. so stops the execution.
 *
 * CoroutineDispatcher determines what thread or threads the corresponding coroutine should be run on.
 *
 * Dispatchers.Default represents a shared pool of threads on JVM
 *
 * To run the coroutine only on the main UI thread, we should specify Dispatchers.Main
 *
 * it's considered good practice to use the dispatcher from the outer scope
 * rather than to explicitly specify it on each end-point.
 * Thus this solution is much more flexible.
 *
 * An alternative but more verbose way to express this would be to start
 * a new coroutine and explicitly wait (by suspending) until it completes:
 * launch(context) { ... }.join().
 *
 */

fun main() {
    ex7()
    ex8()
    ex9()
    ex10()
}

fun ex7() = runBlocking {
    val deferred = async {
        loadData()
    }
    println("Waiting...")
    println(deferred.await())
    println("Done.")
}

suspend fun loadData(): Int {
    println("Loading...")
    delay(5000L)
    println("Loaded.")
    return 42
}

// If there is a list of deferred objects,
// it is possible to call awaitAll to await the results of all of them
fun ex8() = runBlocking {
    doListAll()
}

suspend fun doListAll() = coroutineScope {
    val deferredList = (1..3).map {
        async {
            delay(1000L * it)
            println("Loading $it")
            it
        }
    }
    val sum = deferredList.awaitAll().sum()
    print("$sum")
}

fun ex9() = runBlocking {
    doDispatch()
}

suspend fun doDispatch() = coroutineScope {
    val deferred = async(Dispatchers.Default) {
        println("Starting...")
        delay(1000L)
        println("Done.")
    }
    deferred.await()
}

fun ex10() = runBlocking {
    launch(Dispatchers.Default) {
        val list = doLoadData()
        withContext(Dispatchers.Main) { // crashes on jvm but its ok.
            updateListUi(list)
        }
    }
}

// start coroutines in the inherited context
suspend fun doLoadData() = coroutineScope {
    val deferred = async {
        delay(1000L)
        listOf(1, 2, 3)
    }
    deferred.await()
}

// should be called on the main UI thread.
// so we call it with the context of Dispatchers.Main
fun updateListUi(list: List<Int>) = list.forEach { println(it) }
