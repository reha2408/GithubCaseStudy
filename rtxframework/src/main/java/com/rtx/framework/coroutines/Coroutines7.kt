package com.rtx.framework.coroutines

import kotlinx.coroutines.*

/**
 * The timeout event in withTimeout is asynchronous with respect to the code running in its block and
 * may happen at any time, even right before the return from inside of the timeout block.
 */
fun main() {
    ex23()
    ex24()
    // Outside of runBlocking all coroutines have completed
    println(acquired) // Print the number of resources still acquired
}

var acquired = 0

class Resource {
    init { acquired++ } // Acquire the resource
    fun close() { acquired-- } // Release the resource
}

/**
 * Keep this in mind if you open or acquire some resource inside the block that
 * needs closing or release outside of the block.
 */
fun ex23() = runBlocking {
    repeat(100_000) { // Launch 100K coroutines
        launch {
            val resource = withTimeout(60) { // Timeout of 60 ms
                delay(50) // Delay for 50 ms
                Resource() // Acquire a resource and return it from withTimeout block
            }
            resource.close() // Release the resource
        }
    }
}

/**
 * To workaround this problem you can store a reference to the resource in the variable as opposed to
 * returning it from the withTimeout block.
 */
fun ex24() = runBlocking {
    repeat(100_000) { // Launch 100K coroutines
        launch {
            var resource: Resource? = null // Not acquired yet
            try {
                withTimeout(60) { // Timeout of 60 ms
                    delay(50) // Delay for 50 ms
                    resource = Resource() // Store a resource to the variable if acquired
                }
                // We can do something else with the resource here
            } finally {
                resource?.close() // Release the resource if it was acquired
            }
        }
    }
}