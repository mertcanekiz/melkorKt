package melkorkt.events

abstract class KeyEvent(val keyCode : Int) : Event() {
    override fun getCategoryFlags() = EventCategory.KEYBOARD or EventCategory.INPUT
}

class KeyPressedEvent(keyCode: Int, private val repeatCount : Int) : KeyEvent(keyCode) {
    override fun getEventType() = EventType.KEY_PRESSED
    override fun getName() = "KeyPressedEvent"
    override fun toString() = "${getName()}: $keyCode ($repeatCount repeats)"
}

class KeyReleasedEvent(keyCode: Int) : KeyEvent(keyCode) {
    override fun getEventType() = EventType.KEY_RELEASED
    override fun getName() = "KeyReleasedEvent"
    override fun toString() = "${getName()}: $keyCode"
}

class KeyTypedEvent(keyCode: Int) : KeyEvent(keyCode) {
    override fun getEventType() = EventType.KEY_TYPED
    override fun getName() = "KeyTypedEvent"
    override fun toString() = "${getName()}: $keyCode"
}