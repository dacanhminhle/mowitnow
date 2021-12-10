package com.mowitnow.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Enumeration for different orientations.
 */
public enum Orientation {

    /** Orientation to the North. */
    NORTH("N"),
    /** Orientation to the East. */
    EAST("E"),
    /** Orientation to the South. */
    SOUTH("S"),
    /** Orientation to the West. */
    WEST("W");

    /** Map between notations and the corresponding orientations. */
    private static final Map<String, Orientation> NOTATION_MAP = new HashMap<>();

    static {
        for (final Orientation orientation : values()) {
            NOTATION_MAP.put(orientation.getNotation(), orientation);
        }
    }

    /** The notation for the orientation. */
    private final String notation;

    /**
     * Constructor.
     * @param notation See {@link Orientation#notation}.
     */
    Orientation(final String notation) {
        this.notation = notation;
    }

    /**
     * @return {@link Orientation#notation}.
     */
    public String getNotation() {
        return notation;
    }

    /**
     * Retrieves the corresponding {@link Orientation} of a given notation.
     * @param notation The given notation.
     * @return The corresponding {@link Orientation}.
     */
    public static Optional<Orientation> fromNotation(final String notation) {
        return Optional.ofNullable(NOTATION_MAP.get(notation));
    }

    /**
     * Computes and returns the {@link Orientation} to the right of this {@link Orientation}, considering moving clockwise.
     * @return The {@link Orientation} to the right of this {@link Orientation} (clockwise).
     */
    public Orientation getRightOrientation() {
        return getOrientationWithClockwise(1);
    }

    /**
     * Computes and returns the {@link Orientation} to the left of this {@link Orientation}, considering moving clockwise.
     * @return The {@link Orientation} to the left of this {@link Orientation} (clockwise).
     */
    public Orientation getLeftOrientation() {
        return getOrientationWithClockwise(-1);
    }

    /**
     * Computes and returns the {@link Orientation} having some moving distance with this {@link Orientation},
     * considering moving clockwise.
     * @param ordinalMove The move to make, considering the ordinal positions of the values within the {@link Orientation} enum.
     * @return The corresponding {@link Orientation} after ordinal movement.
     */
    private Orientation getOrientationWithClockwise(final int ordinalMove) {
        int newOrientationOrdinal = (ordinal() + ordinalMove) % getValuesCount();

        if (newOrientationOrdinal < 0) {
            newOrientationOrdinal += getValuesCount();
        }

        return values()[newOrientationOrdinal];
    }

    /**
     * @return The number of values within the {@link Orientation} enum.
     */
    private int getValuesCount() {
        return values().length;
    }

}
