package me.geuxy.entity;

import me.geuxy.util.RenderUtil;

public final class Player {

    public float x, y, lastX, lastY, size;
    private float motionY;
    private float gravity;
    private float fallDistance;

    private int jumpTicks;
    public boolean jumped;

    private int score;

    public Player() {
        this.gravity = 0.25F;
    }

    public void draw() {
        RenderUtil.drawQuad(x, y, size, size, 0xFFFF0000);
    }

    public boolean update(int screenH) {
        lastX = x;
        lastY = y;

        motionY -= gravity;

        if(lastY > y) {
            fallDistance -= lastY - y;
        } else {
            fallDistance = 0;
        }

        motionY -= fallDistance / 2;

        if(jumpTicks == 0 && !jumped) {
            motionY = 4.5F;
            this.jumped = true;
        }

        y -= motionY;

        jumpTicks++;

        return y + size >= screenH || y <= 0;
    }

    public void centerPosition(float width, float height) {
        this.x = (width / 2) - (size / 2);
        this.y = (height / 2) - (size / 2);
    }

    public void increaseScore() {
        score++;
    }

    public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void jump() {
        this.jumpTicks = 0;
    }

    public void setSize(float size) {
        this.size = size;
    }

}
