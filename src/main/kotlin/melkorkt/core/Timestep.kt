package melkorkt.core

class Timestep(private val time: Float = 0.0f) {
    fun seconds() : Float = time
    fun milliseconds() : Float = time * 1000.0f
}