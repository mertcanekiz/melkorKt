package melkorkt.events

class WindowCloseEvent : Event() {
    override fun getEventType() = EventType.WINDOW_CLOSE
    override fun getName() = "WindowCloseEvent"
    override fun getCategoryFlags() = EventCategory.APPLICATION
}

class WindowResizeEvent(private val width: Int, private val height: Int) : Event() {
    override fun getEventType() = EventType.WINDOW_RESIZE
    override fun getName() = "WindowResizeEvent"
    override fun getCategoryFlags() = EventCategory.APPLICATION
    override fun toString() = "${getName()}: $width, $height"
}