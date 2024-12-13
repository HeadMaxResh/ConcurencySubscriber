package org.example.processor

import message.MessageChannel
import kotlin.concurrent.thread

class Processor(private val messageChannel: MessageChannel, private val writers: List<String>) {

    fun process() {
        val writerThreads = writers.map { message ->
            thread {
                messageChannel.write(message)
            }
        }

        val readerThreads = (1..writers.size).map {
            thread {
                messageChannel.read()
            }
        }

        writerThreads.forEach { it.join() }
        readerThreads.forEach { it.join() }
    }
}