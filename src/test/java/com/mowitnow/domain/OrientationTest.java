package com.mowitnow.domain;

import org.junit.jupiter.api.Test;

import static com.mowitnow.domain.Orientation.fromNotation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link Orientation}.
 */
class OrientationTest {

    @Test
    public void testPojo() {
        assertEquals("N", Orientation.NORTH.getNotation());
        assertEquals("E", Orientation.EAST.getNotation());
        assertEquals("S", Orientation.SOUTH.getNotation());
        assertEquals("W", Orientation.WEST.getNotation());
    }

    @Test
    public void testFromNotation() {
        assertEquals(Orientation.NORTH, fromNotation("N").orElse(null));
        assertEquals(Orientation.EAST, fromNotation("E").orElse(null));
        assertEquals(Orientation.SOUTH, fromNotation("S").orElse(null));
        assertEquals(Orientation.WEST, fromNotation("W").orElse(null));
        assertTrue(fromNotation("A").isEmpty());
    }

    @Test
    public void testGetRightOrientation() {
        assertEquals(Orientation.EAST, Orientation.NORTH.getRightOrientation());
        assertEquals(Orientation.SOUTH, Orientation.EAST.getRightOrientation());
        assertEquals(Orientation.WEST, Orientation.SOUTH.getRightOrientation());
        assertEquals(Orientation.NORTH, Orientation.WEST.getRightOrientation());
    }

    @Test
    public void testGetLeftOrientation() {
        assertEquals(Orientation.WEST, Orientation.NORTH.getLeftOrientation());
        assertEquals(Orientation.NORTH, Orientation.EAST.getLeftOrientation());
        assertEquals(Orientation.EAST, Orientation.SOUTH.getLeftOrientation());
        assertEquals(Orientation.SOUTH, Orientation.WEST.getLeftOrientation());
    }

}
