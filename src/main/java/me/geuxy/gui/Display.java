package me.geuxy.gui;

import me.geuxy.input.KeyListener;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public final class Display {

    private static long address;

    private static KeyListener keyListener;

    public static void create(String title, int[] size) {
        if(!glfwInit() || address != 0) {
            throw new IllegalStateException("Failed to create display!");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        address = glfwCreateWindow(size[0], size[1], title, MemoryUtil.NULL, MemoryUtil.NULL);

        if(address == MemoryUtil.NULL) {
            throw new IllegalStateException("Failed to create GLFW Window!");
        }

        glfwMakeContextCurrent(address);
        glfwSwapInterval(1);
        glfwShowWindow(address);

        GL.createCapabilities();

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, size[0], size[1], 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glDisable(GL_DEPTH_TEST);
    }

    public static void poll() {
        glfwPollEvents();
    }

    public static void swapBuffers() {
        glfwSwapBuffers(address);
    }

    public static void destroy() {
        Callbacks.glfwFreeCallbacks(address);

        glfwDestroyWindow(address);
        glfwTerminate();
    }

    public static boolean isRunning() {
        return !glfwWindowShouldClose(address);
    }

    public static void clearScreen() {
        GL11.glClearColor(0, 0, 0, 0);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public static void setKeyListener(KeyListener listener) {
        keyListener = listener;
        glfwSetKeyCallback(address, listener::callback);
    }

}
