package melkorkt.imgui

import imgui.ConfigFlag
import imgui.ImGui
import imgui.ImGui.io
import imgui.imgui.Context
import imgui.impl.gl.ImplGL3
import imgui.impl.glfw.ImplGlfw
import melkorkt.core.Application
import melkorkt.core.Layer
import uno.glfw.GlfwWindow

class ImGuiLayer : Layer("ImGuiLayer") {
    private var implGL3 : ImplGL3? = null
    private var implGlfw : ImplGlfw? = null
    private var ctx : Context? = null
    private var show = true
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