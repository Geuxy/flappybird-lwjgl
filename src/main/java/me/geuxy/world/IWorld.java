package me.geuxy.world;

import me.geuxy.util.BorderList;
import me.geuxy.world.randomizer.Randomizer;

public interface IWorld {

    BorderList getBorders();
    Randomizer getRandomizer();
    void setRandomizer(Randomizer randomizer);

    void draw();

    boolean update();

    void summonBorder(int tileSize, int windowWidth, int windowHeight);
}
