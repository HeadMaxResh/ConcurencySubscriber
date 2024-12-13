package message

import message.MessageChannel

class WithChannel: MessageChannel {

    private val buffer = java.util.concurrent.ArrayBlockingQueue<String>(1)

    override fun write(message: String) {
        println("Writer writing: $message")
        buffer.put(message)
    }

    override fun read(): String? {
        val message = buffer.take()
        println("Reader reading: $message")
        return message
    }

}