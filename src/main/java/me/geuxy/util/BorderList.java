package me.geuxy.util;

import me.geuxy.entity.Border;

import java.util.LinkedList;

public final class BorderList extends LinkedList<Border> {

    @Override
    public boolean add(Border t) {
        if(size() >= 3) {
            this.remove(0);
        }
        return super.add(t);
    }

}
