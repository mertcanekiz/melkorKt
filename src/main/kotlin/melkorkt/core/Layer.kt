package melkorkt.core

import melkorkt.events.Event

abstract class Layer (val name: String = "Layer") {
    open fun onAttach() {}
    open fun onDetach() {}
    open fun onUpdate(ts: Timestep) {}
    open fun onImGuiRender() {}
    open fun onEvent(event: Event) {}
}