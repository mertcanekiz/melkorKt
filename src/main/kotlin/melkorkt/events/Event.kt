package melkorkt.events

enum class EventType {
    NONE,
    WINDOW_CLOSE, WINDOW_RESIZE, WINDOW_LOST_FOCUS, WINDOW_MOVED,
    APP_TICK, APP_UPDATE, APP_RENDER,
    KEY_PRESSED, KEY_RELEASED, KEY_TYPED,
    MOUSE_BUTTON_PRESSED, MOUSE_BUTTON_RELEASED, MOUSE_MOVED, MOUSE_SCROLLED
}

class EventCategory(val categoryCode: Int) {

    companion object {
        val NONE = EventCategory(0)
        val APPLICATION = EventCategory(1 shl 1)
        val INPUT = EventCategory(1 shl 2)
        val KEYBOARD = EventCategory(1 shl 3)
        val MOUSE = EventCategory(1 shl 4)
        val MOUSE_BUTTON = EventCategory(1 shl 5)
    }

    infix fun or(other: EventCategory) : EventCategory {
        return EventCategory(this.categoryCode or other.categoryCode)
    }

    infix fun and(other: EventCategory) : EventCategory {
        return EventCategory(this.categoryCode and other.categoryCode)
    }
}



abstract class Event {
    var handled: Boolean = false
    abstract fun getEventType() : EventType
    abstract fun getName() : String
    abstract fun getCategoryFlags() : EventCategory
    override fun toString() : String = getName()
    fun isInCategory(category: EventCategory) : Boolean {
        return (getCategoryFlags() and category) != EventCategory.NONE
    }
}