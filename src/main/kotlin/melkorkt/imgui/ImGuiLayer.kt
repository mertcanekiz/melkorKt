package melkorkt.imgui

import imgui.ConfigFlag
import imgui.ImGui
import imgui.imgui.Context
import imgui.ImGui.io
import imgui.MutableProperty0
import imgui.impl.gl.ImplGL3
import imgui.MINECRAFT_BEHAVIORS
import imgui.impl.glfw.ImplGlfw
import melkorkt.core.Application
import melkorkt.core.Layer
import uno.glfw.GlfwWindow
import glm_.vec2.Vec2i
import imgui.impl.GlfwClientApi
import kotlin.reflect.KMutableProperty0

class ImGuiLayer : Layer("ImGuiLayer") {
    val time: Float = 0.0f
    var implGL3 : ImplGL3? = null
    var implGlfw : ImplGlfw? = null
    var ctx : Context? = null
    var show = true
    override fun onAttach() {
        ctx = Context()
        val window = Application.instance.window.window
        io.configFlags = io.configFlags or ConfigFlag.NavEnableKeyboard.i
        io.configFlags = io.configFlags or ConfigFlag.NavEnableGamepad.i
        ImGui.styleColorsDark()
        implGlfw = ImplGlfw(GlfwWindow.from(window), false)
        implGL3 = ImplGL3()
    }
    override fun onDetach() {
        implGL3?.shutdown()
        implGlfw?.shutdown()
        ctx?.destroy()
    }
    override fun onImGuiRender() {
        if (show)
            ImGui.showDemoWindow(::show)
    }
    fun begin() {
        implGL3?.newFrame()
        implGlfw?.newFrame()
        ImGui.newFrame()
    }

    fun end() {
        io.displaySize = glm_.vec2.Vec2i(Application.instance.window.width, Application.instance.window.height)
        ImGui.render()
        implGL3?.renderDrawData(ImGui.drawData!!)
    }
}