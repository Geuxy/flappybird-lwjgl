package me.geuxy.world.randomizer;

import me.geuxy.entity.Border;

public final class DefaultRandomizer implements Randomizer {

    @Override
    public int[] handle(Border border, int tileSize, int windowWidth, int windowHeight) {
        return new int[] {windowWidth, range(0, windowHeight - tileSize)};
    }

    public int range(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

}
