package com.mowitnow.exceptions;

import com.mowitnow.domain.Orientation;

/**
 * Exception thrown when encountering an unknown {@link Orientation}.
 */
public class UnknownOrientationException extends RuntimeException {

    /**
     * Constructor providing an {@link Orientation}.
     * @param orientation The given {@link Orientation}.
     */
    public UnknownOrientationException(final Orientation orientation) {
        super("Unknown orientation: " + orientation);
    }

    /**
     * Constructor providing a String notation.
     * @param notation The given notation.
     */
    public UnknownOrientationException(final String notation) {
        super("Unknown orientation, with the following notation: " + notation);
    }

}
