package com.rtx.framework.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

/**
 * Writing code with a shared mutable state is known to be quite difficult and error-prone.
 *
 * Sharing information by communication
 * instead of sharing information using the common mutable state
 * tries to simplify this.
 *
 * Coroutines can communicate with each other via channels.
 *
 * A coroutine that sends (produces) information is often called a producer.
 * A coroutine that receives (consumes) information is called a consumer.
 *
 * Note that: when many coroutines receive information from the same channel,
 * each element is handled only once by one of the consumers; (works like queue)
 * handling it automatically means removing this element from the channel.
 *
 * A channel can suspend send and receive operations.
 * This happens when the channel is empty or full (the channel's size might be constrained, and then it can be full).
 *
    interface SendChannel<in E> {
        suspend fun send(element: E)
        fun close(): Boolean
    }

    interface ReceiveChannel<out E> {
        suspend fun receive(): E
    }

    interface Channel<E> : SendChannel<E>, ReceiveChannel<E>
 *
 * Create a channel and give it;
 * to producers as a SendChannel instance
 * to consumers as ReceiveChannel instance
 *
 * The producer can close a channel to indicate that no more elements are coming.
 *
 * Several types of channels are defined in the library, differ in each other;
 *  - how many elements they can internally store
 *  - whether the send call can suspend or not
 *  - receive call behaves in the same manner:
 *    it receives an element if the channel is not empty, and otherwise suspends
 *
 * Unlimited channel
 *  - producers can send elements to this channel, and it will grow infinitely,
 *    until get an OutOfMemoryException.
 *  - send call will never be suspended.
 *  - when a consumer tries to receive from an empty channel and gets suspended
 *    until some new elements are sent to this channel.
 *
 * Buffered Channel
 *   - A buffered channel's size is constrained by the specified number.
 *   - When the channel is full,
 *     the next send call on it suspends until more free space appears.
 *
 * “Rendezvous” channel
 *   - a buffered channel with zero size.
 *   - send or receive always gets suspended until the other is called.
 *
 *  Conflated channel
 *   - A new element sent to the conflated channel will overwrite the previously sent element.
 *   - so the receiver will always get only the latest element.
 *   - send call will never suspend.
 *
    val rendezvousChannel = Channel<String>() // By default, a "Rendezvous" channel is created.
    val bufferedChannel = Channel<String>(10)
    val conflatedChannel = Channel<String>(CONFLATED)
    val unlimitedChannel = Channel<String>(UNLIMITED)
 *
 *
 */

fun main() {
    ex13()
    ex14()
}

/**
 * A1
 * B1
 * A done
 * B done
 * A2
 */
fun ex13() = runBlocking {
    val channel = Channel<String>()
    launch {
        channel.send("A1")
        channel.send("A2")
        log("A done")
    }
    launch {
        channel.send("B1")
        log("B done")
    }
    launch {
        repeat(3) {
            val x = channel.receive()
            log(x)
        }
    }
}

fun log(message: Any?) {
    println("[${Thread.currentThread().name}] $message")
}

/*
[Ahmet, Ali, Mehmet]
[Reha, Ahmet, Ali]
[Reha]
Done.
 */
fun ex14() = runBlocking {
    launch(Dispatchers.Default) {
        loadContributorsChannels { users, completed ->
            withContext(Dispatchers.Default) {
                updateResults(users, completed)
            }
        }
    }
}

suspend fun loadContributorsChannels(
    updateResults: suspend (List<String>, completed: Boolean) -> Unit
) = coroutineScope {
    val repos = getRepoIds()
    val channel = Channel<List<String>>()
    for (id in repos) {
        launch {
            val developers = getContributors(id)
            channel.send(developers)
        }
    }
    repos.forEachIndexed { i, _ ->
        val users = channel.receive()
        updateResults(users, i == repos.lastIndex)
    }
}

suspend fun getRepoIds() = coroutineScope {
    val d = async {
       delay(1000L)
       listOf(1, 2, 3)
    }
    d.await()
}

suspend fun getContributors(repoId: Int) = coroutineScope {
    val d = async {
        val delayTime = (Math.random() * 10000).toLong()
        delay(delayTime)
        when (repoId) {
            1 -> listOf("Reha")
            2 -> listOf("Reha", "Ahmet", "Ali")
            3 -> listOf("Ahmet", "Ali", "Mehmet")
            else -> emptyList()
        }
    }
    d.await()
}

fun updateResults(list: List<String>, completed: Boolean) {
    println(list)
    if (completed) println("Done.")
}
