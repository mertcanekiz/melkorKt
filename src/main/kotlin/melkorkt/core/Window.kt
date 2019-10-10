package melkorkt.core

import melkorkt.events.*

import org.lwjgl.opengl.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.*

data class WindowProps(val title : String = "melkor", val width: Int = 640, val height: Int = 480)

class Window(props: WindowProps) {
    var window: Long
    var eventCallback : ((Event) -> Unit)? = null
    var vsync : Boolean = false
        get() { return field }
        set (enabled) {
            if (enabled) glfwSwapInterval(1)
            else glfwSwapInterval(0)
            field = enabled
        }

    init{
        GLFWErrorCallback.createPrint(System.err).set()
        if (!glfwInit()) {
            println("Could not initialize GLFW")
        }

        window = glfwCreateWindow(props.width, props.height, props.title, NULL, NULL)

        glfwSetWindowCloseCallback(window) { _ ->
            eventCallback!!.invoke(WindowCloseEvent())
        }

        glfwSetWindowSizeCallback(window) { _, width , height ->
            eventCallback!!.invoke(WindowResizeEvent(width, height))
        }

        glfwSetKeyCallback(window) { window, key, scanCode, action, mods ->
            when (action) {
                GLFW_PRESS -> eventCallback!!.invoke(KeyPressedEvent(key, 0))
                GLFW_RELEASE -> eventCallback!!.invoke(KeyReleasedEvent(key))
                GLFW_REPEAT -> eventCallback!!.invoke(KeyPressedEvent(key, 1))
            }
        }

        glfwSetCharCallback(window) { window, keyCode ->
            eventCallback!!.invoke(KeyTypedEvent(keyCode))
        }

        glfwSetMouseButtonCallback(window) { window, button, action, mods ->
            when (action) {
                GLFW_PRESS -> eventCallback!!.invoke(MouseButtonPressedEvent(button))
                GLFW_RELEASE -> eventCallback!!.invoke(MouseButtonReleasedEvent(button))
            }
        }

        glfwSetScrollCallback(window) { window, xOffset, yOffset ->
            eventCallback!!.invoke(MouseScrolledEvent(xOffset, yOffset))
        }

        glfwSetCursorPosCallback(window) { window, xPos, yPos ->
            eventCallback!!.invoke(MouseMovedEvent(xPos, yPos))
        }

        glfwMakeContextCurrent(window)
        vsync = true
        glfwShowWindow(window)
        GL.createCapabilities()
        glClearColor(0f, 0.5f, 0.5f, 1.0f)
    }

    fun destroy() {
        glfwDestroyWindow(window)
    }

    fun onUpdate() {
        glfwPollEvents()
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        glfwSwapBuffers(window)
    }



}