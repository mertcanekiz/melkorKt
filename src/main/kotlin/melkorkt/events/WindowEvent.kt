package melkorkt.events

class WindowCloseEvent : Event() {
    override fun getEventType() = EventType.WINDOW_CLOSE
    override fun getName() = "WindowCloseEvent"
    override fun getCategoryFlags() = EventCategory.APPLICATION
}

class WindowResizeEvent(val width: Int, val height: Int) : Event() {
    override fun getEventType() = EventType.WINDOW_RESIZE
    override fun getName() = "WindowResizeEvent"
    override fun getCategoryFlags() = EventCategory.APPLICATION
}