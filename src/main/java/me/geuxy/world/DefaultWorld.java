package me.geuxy.world;

import me.geuxy.Game;
import me.geuxy.entity.Border;
import me.geuxy.util.BorderList;
import me.geuxy.world.randomizer.DefaultRandomizer;
import me.geuxy.world.randomizer.Randomizer;

public final class DefaultWorld implements IWorld {

    private final BorderList borders;

    private Randomizer randomizer;

    public DefaultWorld() {
        this.borders = new BorderList();
        this.randomizer = new DefaultRandomizer();
    }

    @Override
    public void draw() {
        borders.forEach(Border::draw);
    }

    @Override
    public boolean update() {
        return borders.stream().anyMatch(b -> b.update(Game.getGame().getPlayer()));
    }

    @Override
    public void summonBorder(int tileSize, int windowWidth, int windowHeight) {
        Border border = new Border();

        int[] pos = randomizer.handle(border, tileSize, windowWidth, windowHeight);
        border.setSize(tileSize, pos[0], pos[1], windowHeight);
        borders.add(border);
    }

    @Override
    public BorderList getBorders() {
        return borders;
    }

    @Override
    public Randomizer getRandomizer() {
        return randomizer;
    }

    @Override
    public void setRandomizer(Randomizer randomizer) {
        this.randomizer = randomizer;
    }

}
