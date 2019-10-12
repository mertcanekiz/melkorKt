package melkorkt.core

import melkorkt.events.Event
import melkorkt.events.EventDispatcher
import melkorkt.events.EventType
import melkorkt.events.WindowCloseEvent
import melkorkt.imgui.ImGuiLayer

import org.lwjgl.glfw.GLFW.glfwGetTime

class Application {
    val window : Window = Window(WindowProps())
    var running : Boolean = true
    val layerStack = LayerStack()
    var lastFrameTime : Float = 0.0f
    val imGuiLayer : ImGuiLayer
    companion object {
        val instance = Application()
    }

    init {
        window.eventCallback = ::onEvent
        imGuiLayer = ImGuiLayer()
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
        pushOverlay(imGuiLayer)
        while (running) {
            window.clear()
            val time = glfwGetTime().toFloat()
            val timestep = Timestep(time - lastFrameTime)
            lastFrameTime = time
            layerStack.layers.forEach { it.onUpdate(timestep) }
            imGuiLayer.begin()
            layerStack.layers.forEach { it.onImGuiRender() }
            imGuiLayer.end()
            window.onUpdate()
        }
        window.destroy()
    }

    fun pushLayer(layer: Layer) = layerStack.pushLayer(layer)
    fun pushOverlay(overlay: Layer) = layerStack.pushOverlay(overlay)
}