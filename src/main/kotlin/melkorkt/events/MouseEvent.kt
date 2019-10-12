package melkorkt.events

abstract class MouseButtonEvent(val button : Int): Event() {
    override fun getCategoryFlags() = EventCategory.MOUSE or EventCategory.MOUSE_BUTTON or EventCategory.INPUT
}

class MouseButtonPressedEvent(button: Int) : MouseButtonEvent(button) {
    override fun getEventType() = EventType.MOUSE_BUTTON_PRESSED
    override fun getName() = "MouseButtonPressedEvent"
    override fun toString() = "${getName()}: $button"
}

class MouseButtonReleasedEvent(button: Int) : MouseButtonEvent(button) {
    override fun getEventType() = EventType.MOUSE_BUTTON_RELEASED
    override fun getName() = "MouseButtonReleasedEvent"
    override fun toString() = "${getName()}: $button"
}

class MouseMovedEvent(private val x: Double, private val y : Double) : Event() {
    override fun getEventType() = EventType.MOUSE_MOVED
    override fun getName() = "MouseMovedEvent"
    override fun getCategoryFlags() = EventCategory.MOUSE or EventCategory.INPUT
    override fun toString() = "${getName()}: $x, $y"
}

class MouseScrolledEvent(private val xOffset: Double, private val yOffset : Double) : Event() {
    override fun getEventType() = EventType.MOUSE_SCROLLED
    override fun getName() = "MouseScrolledEvent"
    override fun getCategoryFlags() = EventCategory.MOUSE or EventCategory.INPUT
    override fun toString() = "${getName()}: $xOffset, $yOffset"
}