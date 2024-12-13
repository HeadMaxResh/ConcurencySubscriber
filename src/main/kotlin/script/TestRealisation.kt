package org.example.script

import org.example.processor.Processor
import message.WithChannel
import message.WithCountDownLatch
import message.WithMutex
import java.util.concurrent.CountDownLatch

fun runApp() {
    val writers = listOf("AAAA1", "BBBB1", "CCCC1", "AAAA2", "BBBB2", "CCCC2")

    val latch = CountDownLatch(writers.size)

    println("Without synchronization:")
    Processor(WithCountDownLatch(latch), writers).process()

    println("\nWith Mutex:")
    Processor(WithMutex(), writers).process()

    println("\nWith Channel:")
    Processor(WithChannel(), writers).process()

    println("\nWith CountDownLatch:")
    Processor(WithCountDownLatch(latch), writers).process()
}
