package melkorkt.core

import melkorkt.events.*

import org.lwjgl.opengl.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.*

data class WindowProps(var title : String = "melkor", var width: Int = 1280, var height: Int = 720)

class Window(private val props: WindowProps) {
    var window: Long
    var eventCallback : ((Event) -> Unit)? = null
    private var vsync : Boolean = false
        set (enabled) {
            if (enabled) glfwSwapInterval(1)
            else glfwSwapInterval(0)
            field = enabled
        }
    var width : Int
        get() { return props.width }
        set(width) { props.width = width }
    var height : Int
        get() { return props.height }
        set(height) { props.height = height }

    init{
        GLFWErrorCallback.createPrint(System.err).set()
        if (!glfwInit()) {
            println("Could not initialize GLFW")
        }
        println("GLFW init")

        window = glfwCreateWindow(props.width, props.height, props.title, NULL, NULL)

        println("window created $window")

        glfwSetWindowCloseCallback(window) {
            eventCallback?.invoke(WindowCloseEvent())
        }

        glfwSetWindowSizeCallback(window) { _, width , height ->
            eventCallback?.invoke(WindowResizeEvent(width, height))
        }

        glfwSetKeyCallback(window) { _, key, _, action, _ ->
            when (action) {
                GLFW_PRESS -> eventCallback?.invoke(KeyPressedEvent(key, 0))
                GLFW_RELEASE -> eventCallback?.invoke(KeyReleasedEvent(key))
                GLFW_REPEAT -> eventCallback?.invoke(KeyPressedEvent(key, 1))
            }
        }

        glfwSetCharCallback(window) { _, keyCode ->
            eventCallback?.invoke(KeyTypedEvent(keyCode))
        }

        glfwSetMouseButtonCallback(window) { _, button, action, _ ->
            when (action) {
                GLFW_PRESS -> eventCallback?.invoke(MouseButtonPressedEvent(button))
                GLFW_RELEASE -> eventCallback?.invoke(MouseButtonReleasedEvent(button))
            }
        }

        glfwSetScrollCallback(window) { _, xOffset, yOffset ->
            eventCallback?.invoke(MouseScrolledEvent(xOffset, yOffset))
        }

        glfwSetCursorPosCallback(window) { _, xPos, yPos ->
            eventCallback?.invoke(MouseMovedEvent(xPos, yPos))
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

    fun clear() {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
    }

    fun onUpdate() {
        glfwPollEvents()
        glfwSwapBuffers(window)
    }



}