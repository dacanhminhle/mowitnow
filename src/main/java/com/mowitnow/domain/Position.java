package com.mowitnow.domain;

/**
 * Position on a {@link Lawn}, including a horizontal coordinate and a vertical coordinate.
 */
public class Position {

    /** Position's horizontal coordinate. */
    private int x;
    /** Position's vertical coordinate. */
    private int y;

    /**
     * Constructor.
     * @param x See {@link Position#x}.
     * @param y See {@link Position#y}.
     */
    public Position(final int x, final int y) {
        setX(x);
        setY(y);
    }

    /**
     * Constructor with a provided {@link Point}.
     * @param point The provided {@link Point}.
     */
    public Position(final Point point) {
        this(point.getX(), point.getY());
    }

    /**
     * @return {@link Position#x}.
     */
    public int getX() {
        return x;
    }

    /**
     * @return {@link Position#y}.
     */
    public int getY() {
        return y;
    }

    /**
     * @param x See {@link Position#x}.
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * @param y See {@link Position#y}.
     */
    public void setY(final int y) {
        this.y = y;
    }

}
