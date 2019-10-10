package melkorkt.events

class EventDispatcher(val event: Event) {
    inline fun <reified T> dispatch(callback: (Event) -> Boolean) : Boolean {
        if (event::class.java == T::class.java) {
            event.handled = callback.invoke(event)
            return true
        }
        return false
    }
}