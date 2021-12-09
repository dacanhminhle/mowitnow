package com.mowitnow.domain;

/**
 * A point on a {@link Lawn}'s grid.
 */
public class Point {

    /** Point's horizontal coordinate. */
    private final int x;
    /** Point's vertical coordinate. */
    private final int y;

    /**
     * Constructor.
     * @param x See {@link Point#x}.
     * @param y See {@link Point#y}.
     */
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return {@link Point#x}.
     */
    public int getX() {
        return x;
    }

    /**
     * @return {@link Point#y}.
     */
    public int getY() {
        return y;
    }

}
