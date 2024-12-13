package message

import java.util.concurrent.CountDownLatch

class WithCountDownLatch(private val latch: CountDownLatch) : MessageChannel {

    private var buffer: String? = null

    override fun write(message: String) {
        if (buffer == null) {
            println("Writer writing: $message")
            buffer = message
        }
    }

    override fun read(): String? {
        val message = buffer
        if (message != null) {
            println("Reader reading: $message")
            buffer = null
        }
        return message
    }

}