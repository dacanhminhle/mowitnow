package com.mowitnow.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link Position}.
 */
class PositionTest {

    @Test
    public void testPojo() {
        final int x = 2;
        final int y = 3;
        final Position sut = new Position(new Point(x, y));

        assertEquals(x, sut.getX());
        assertEquals(y, sut.getY());
    }

}
