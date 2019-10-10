package melkorkt.events

class AppUpdateEvent : Event() {
    override fun getEventType() = EventType.APP_UPDATE
    override fun getName() = "AppUpdateEvent"
    override fun getCategoryFlags() = EventCategory.APPLICATION
}

class AppTickEvent : Event() {
    override fun getEventType() = EventType.APP_TICK
    override fun getName() = "AppTickEvent"
    override fun getCategoryFlags() = EventCategory.APPLICATION
}

class AppRenderEvent : Event() {
    override fun getEventType() = EventType.APP_RENDER
    override fun getName() = "AppRenderEvent"
    override fun getCategoryFlags() = EventCategory.APPLICATION
}