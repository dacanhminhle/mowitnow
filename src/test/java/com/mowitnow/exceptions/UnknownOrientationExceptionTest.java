package com.mowitnow.exceptions;

import com.mowitnow.domain.Orientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link UnknownOrientationException}.
 */
class UnknownOrientationExceptionTest {

    private UnknownOrientationException sut;

    @Test
    public void testConstructorWithOrientation() {
        final Orientation orientation = Orientation.NORTH;
        sut = new UnknownOrientationException(orientation);

        assertEquals("Unknown orientation: " + orientation, sut.getMessage());
    }

    @Test
    public void testConstructorWithNotation() {
        final String notation = "N";
        sut = new UnknownOrientationException(notation);

        assertEquals("Unknown orientation, with the following notation: " + notation, sut.getMessage());
    }

}
