package me.geuxy.input;

public interface KeyListener {

    void callback(long address, int key, int code, int action, int mods);

    boolean isKeyPressed(int key);

}
