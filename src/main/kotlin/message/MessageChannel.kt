package message

interface MessageChannel {

    fun write(message: String)
    fun read(): String?

}