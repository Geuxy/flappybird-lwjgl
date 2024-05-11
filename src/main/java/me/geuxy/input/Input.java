package me.geuxy.input;

import org.lwjgl.glfw.GLFW;

public final class Input implements KeyListener {

    private final boolean[] keyPressed = new boolean[350];

    @Override
    public void callback(long address, int key, int code, int action, int mods) {
        if(action == GLFW.GLFW_PRESS) {
            keyPressed[key] = true;

        } else if(action == GLFW.GLFW_RELEASE) {
            keyPressed[key] = false;
        }
    }

    @Override
    public boolean isKeyPressed(int key) {
        return keyPressed[key];
    }

}
