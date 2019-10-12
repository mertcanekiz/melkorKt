package melkorkt.core

import org.lwjgl.system.MemoryStack

import org.lwjgl.glfw.GLFW.*

object Input {
    fun isKeyPressed(keyCode: Int) : Boolean {
        val window = Application.instance.window.window
        val state = glfwGetKey(window, keyCode)
        return state == GLFW_PRESS || state == GLFW_REPEAT
    }

    fun isMouseButtonPressed(button: Int) : Boolean {
        val window = Application.instance.window.window
        val state = glfwGetMouseButton(window, button)
        return state == GLFW_PRESS
    }

    fun getMousePosition() : Pair<Double, Double> {
        val window = Application.instance.window.window
        val stack = MemoryStack.stackPush()
        val xBuf = stack.mallocDouble(1)
        val yBuf = stack.mallocDouble(1)
        glfwGetCursorPos(window, xBuf, yBuf)
        return Pair(xBuf.get(0), yBuf.get(0))
    }

    fun getMouseX() : Double {
        val window = Application.instance.window.window
        val stack = MemoryStack.stackPush()
        val xBuf = stack.mallocDouble(1)
        glfwGetCursorPos(window, xBuf, null)
        return xBuf.get(0)
    }

    fun getMouseY() : Double {
        val window = Application.instance.window.window
        val stack = MemoryStack.stackPush()
        val yBuf = stack.mallocDouble(1)
        glfwGetCursorPos(window, null, yBuf)
        return yBuf.get(0)
    }
}