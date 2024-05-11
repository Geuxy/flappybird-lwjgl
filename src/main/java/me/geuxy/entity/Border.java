package me.geuxy.entity;

import me.geuxy.util.RenderUtil;

public final class Border {

    public int x, y, lastX, lastY, tileSize, screenHeight;

    private int gap;

    private int speed = 3;

    public void draw() {
        RenderUtil.drawQuad(x, 0, tileSize, y - gap, 0xFF00FF00);
        RenderUtil.drawQuad(x, y + gap, tileSize, screenHeight, 0xFF00FF00);
    }

    public boolean update(Player player) {
        lastX = x;
        lastY = y;
        x -= speed;

        boolean sameX = (player.x + tileSize) >= x && player.x <= (x + tileSize);
        boolean sameY = player.y >= (y - gap) && (player.y + tileSize) <= (y + gap);

        return sameX && !sameY;
    }

    public void setSize(int tileSize, int x, int y, int screenHeight) {
        this.gap = tileSize * 2;
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
        this.screenHeight = screenHeight;
    }

}
