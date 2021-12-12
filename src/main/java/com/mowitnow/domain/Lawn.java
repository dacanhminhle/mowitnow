package com.mowitnow.domain;

/**
 * Lawn to be mowed, represented by a grid of points to simplify navigation.
 * It is limited by the lower limit point (bottom left corner) and the upper limit point (top right corner).
 */
public class Lawn {

    /** The horizontal / vertical coordinate of the origin. */
    private static final int ORIGIN_COORDINATE = 0;

    /** The horizontal coordinate of the lawn's upper limit point. */
    private final int upperLimitX;
    /** The vertical coordinate of the lawn's upper limit point. */
    private final int upperLimitY;

    /**
     * Constructor.
     * @param upperLimitX See {@link Lawn#upperLimitX}.
     * @param upperLimitY See {@link Lawn#upperLimitY}.
     */
    public Lawn(final int upperLimitX, final int upperLimitY) {
        this.upperLimitX = upperLimitX;
        this.upperLimitY = upperLimitY;
    }

    /**
     * @return The horizontal coordinate of the lawn's lower limit point.
     */
    public int getLowerLimitX() {
        return ORIGIN_COORDINATE;
    }

    /**
     * @return The vertical coordinate of the lawn's lower limit point.
     */
    public int getLowerLimitY() {
        return ORIGIN_COORDINATE;
    }

    /**
     * @return {@link Lawn#upperLimitX}.
     */
    public int getUpperLimitX() {
        return upperLimitX;
    }

    /**
     * @return {@link Lawn#upperLimitY}.
     */
    public int getUpperLimitY() {
        return upperLimitY;
    }

    @Override
    public String toString() {
        return "Lawn{"
                + "lowerLimit = (" + getLowerLimitX() + ", " + getLowerLimitY() + ")"
                + "; upperLimit = (" + upperLimitX + ", " + upperLimitY + ")"
                + '}';
    }

}
