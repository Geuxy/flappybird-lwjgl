package me.geuxy.world.randomizer;

import me.geuxy.entity.Border;

public interface Randomizer {

    int[] handle(Border border, int tileSize, int windowWidth, int windowHeight);

}
