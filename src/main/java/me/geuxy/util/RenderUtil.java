package me.geuxy.util;

import static org.lwjgl.opengl.GL11.*;

public final class RenderUtil {

    public static void drawQuad(float x, float y, float width, float height, int color) {
        setColor(color);

        glBegin(GL_QUADS);

        glVertex2f(x, y);
        glVertex2f(x + width, y);
        glVertex2f(x + width, y + height);
        glVertex2f(x, y + height);

        glEnd();
    }

    public static void setColor(int color) {
        float[] rgb = rgb(color);

        glColor3f(rgb[0], rgb[1], rgb[2]);
    }

    public static float[] rgb(int color) {
        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = color & 0xFF;

        return new float[] {
            (float) red / 255,
            (float) green / 255,
            (float) blue / 255
        };
    }

}
