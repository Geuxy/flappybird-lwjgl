package me.geuxy;

import me.geuxy.entity.Player;
import me.geuxy.gui.Display;
import me.geuxy.input.Input;
import me.geuxy.world.DefaultWorld;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Game {

    public static Game game;

    private final Input input;

    private Player player;

    private final int regularTileSize = 16;
    private final int scale = 3;

    private final int tileSize = regularTileSize * scale;

    private final int columns = 14;
    private final int rows = 10;

    private final int width = tileSize * columns;
    private final int height = tileSize * rows;

    private final int fps = 120;
    private final int ups = 60;

    private boolean crashed;

    private long last;

    private DefaultWorld world;

    public Game() {
        game = this;

        this.input = new Input();

        Display.create("Flappy Bird LWJGL", new int[] {width, height});
        Display.setKeyListener(input);

        this.world = new DefaultWorld();

        this.player = new Player();
        this.resetGame();

        this.loop();
    }

    public void loop() {
        long initTime = System.nanoTime();

        final double updateDelta = (double) 1_000_000_000 / ups;
        final double renderDelta = (double) 1_000_000_000 / fps;

        double deltaU = 0;
        double deltaF = 0;

        long time = System.currentTimeMillis();

        last = time;

        while(Display.isRunning()) {
            final long current = System.nanoTime();

            deltaU += (current - initTime) / updateDelta;
            deltaF += (current - initTime) / renderDelta;

            initTime = current;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }

            if(deltaF >= 1) {
                Display.poll();
                Display.clearScreen();

                GL11.glLoadIdentity();
                render();
                Display.swapBuffers();

                deltaF--;
            }

            if (System.currentTimeMillis() - time > 1000) {
                time += 1000;
            }
        }

        Display.destroy();
    }

    public void render() {
        player.draw();

        world.draw();
    }

    public void update() {
        if(!crashed) {
            crashed = player.update(height) || world.update();

            final long current = System.currentTimeMillis();

            if(current - last >= 1750) {
                world.summonBorder(tileSize, width, height);
                last = current;
            }

            if(input.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
                player.jump();
            } else {
                player.jumped = false;
            }
        } else {
            if(input.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
                this.resetGame();
            }
        }
    }

    private void resetGame() {
        this.crashed = false;
        this.player.setSize(tileSize);
        this.player.centerPosition(width, height);
        this.player.resetScore();
        this.world.getBorders().clear();
        this.last = System.currentTimeMillis();
    }

    public static void main(final String[] args) {
        new Game();
    }

    public Player getPlayer() {
        return player;
    }

    public static Game getGame() {
        return game;
    }

}