package melkorkt.core

abstract class Layer (val name: String = "Layer") {
    open fun onAttach() {}
    open fun onDetach() {}
    open fun onUpdate(ts: Timestep) {}
    open fun onImGuiRender() {}
    open fun onEvent() {}
}