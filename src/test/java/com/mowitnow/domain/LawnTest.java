package com.mowitnow.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link Lawn}.
 */
class LawnTest {

    @Test
    public void testPojo() {
        final int originCoordinate = 0;
        final int limitX = 2;
        final int limitY = 3;
        final Lawn sut = new Lawn(limitX, limitY);

        assertEquals(originCoordinate, sut.getLowerLimitX());
        assertEquals(originCoordinate, sut.getLowerLimitY());
        assertEquals(limitX, sut.getUpperLimitX());
        assertEquals(limitY, sut.getUpperLimitY());
    }

}
