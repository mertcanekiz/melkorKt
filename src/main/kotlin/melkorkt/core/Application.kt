package melkorkt.core

import melkorkt.events.Event
import melkorkt.events.EventDispatcher
import melkorkt.events.EventType
import melkorkt.events.WindowCloseEvent

class Application {
    val window : Window = Window(WindowProps())
    var running : Boolean = true
    companion object {
        val instance = Application()
    }

    init {
        window.eventCallback = ::onEvent
    }

    fun onEvent(event: Event) {
        println(event)
        val dispatcher = EventDispatcher(event)
        dispatcher.dispatch<WindowCloseEvent>(::onWindowClose)
    }

    fun onWindowClose(event: Event) : Boolean {
        running = false
        return true
    }

    fun run() {
        while (running) {
            window.onUpdate()
        }
        window.destroy()
    }
}