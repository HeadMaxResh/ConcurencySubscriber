package message

import message.MessageChannel
import java.util.concurrent.locks.ReentrantLock

class WithMutex: MessageChannel {

    private var buffer: String? = null
    private val lock = ReentrantLock()

    override fun write(message: String) {
        lock.lock()
        try {
            if (buffer == null) {
                println("Writer writing: $message")
                buffer = message
            }
        } finally {
            lock.unlock()
        }
    }

    override fun read(): String? {
        lock.lock()
        try {
            val message = buffer
            if (message != null) {
                println("Reader reading: $message")
                buffer = null
            }
            return message
        } finally {
            lock.unlock()
        }
    }

}
