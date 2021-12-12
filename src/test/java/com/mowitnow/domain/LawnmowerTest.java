package com.mowitnow.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link Lawnmower}.
 */
class LawnmowerTest {

    private static final Lawn TEST_LAWN = new Lawn(5, 5);
    private static final Point TEST_INITIAL_POINT = new Point(3, 2);
    private static final Orientation TEST_INITIAL_ORIENTATION = Orientation.NORTH;
    private static final List<Command> TEST_COMMAND_LIST = List.of(Command.TURN_RIGHT, Command.TURN_LEFT, Command.ADVANCE);
    private static final MowingConfiguration TEST_MOWING_CONFIGURATION = new MowingConfiguration(TEST_INITIAL_POINT, TEST_INITIAL_ORIENTATION, TEST_COMMAND_LIST);

    private final Lawnmower sut = new Lawnmower(TEST_MOWING_CONFIGURATION);

    @Test
    public void testPojo() {
        assertEquals(TEST_MOWING_CONFIGURATION, sut.getMowingConfiguration());
        assertEquals(TEST_INITIAL_POINT.getX(), sut.getPosition().getX());
        assertEquals(TEST_INITIAL_POINT.getY(), sut.getPosition().getY());
        assertEquals(TEST_INITIAL_ORIENTATION, sut.getOrientation());
    }

    @Test
    public void testMow() {
        sut.mow(TEST_LAWN);

        assertEquals(TEST_INITIAL_POINT.getX(), sut.getPosition().getX());
        assertEquals(TEST_INITIAL_POINT.getY() + 1, sut.getPosition().getY());
        assertEquals(TEST_INITIAL_ORIENTATION, sut.getOrientation());
    }

    @Test
    public void testAdvance_toNorth() {
        sut.getPosition().setX(TEST_LAWN.getUpperLimitX());
        sut.getPosition().setY(TEST_LAWN.getUpperLimitY() - 1);
        sut.setOrientation(Orientation.NORTH);

        sut.advance(TEST_LAWN);

        assertEquals(TEST_LAWN.getUpperLimitX(), sut.getPosition().getX());
        assertEquals(TEST_LAWN.getUpperLimitY(), sut.getPosition().getY());
    }

    @Test
    public void testAdvance_toNorthAtBorder() {
        sut.getPosition().setX(TEST_LAWN.getUpperLimitX());
        sut.getPosition().setY(TEST_LAWN.getUpperLimitY());
        sut.setOrientation(Orientation.NORTH);

        sut.advance(TEST_LAWN);

        assertEquals(TEST_LAWN.getUpperLimitX(), sut.getPosition().getX());
        assertEquals(TEST_LAWN.getUpperLimitY(), sut.getPosition().getY());
    }

    @Test
    public void testAdvance_toEast() {
        sut.getPosition().setX(TEST_LAWN.getUpperLimitX() - 1);
        sut.getPosition().setY(TEST_LAWN.getUpperLimitY());
        sut.setOrientation(Orientation.EAST);

        sut.advance(TEST_LAWN);

        assertEquals(TEST_LAWN.getUpperLimitX(), sut.getPosition().getX());
        assertEquals(TEST_LAWN.getUpperLimitY(), sut.getPosition().getY());
    }

    @Test
    public void testAdvance_toEastAtBorder() {
        sut.getPosition().setX(TEST_LAWN.getUpperLimitX());
        sut.getPosition().setY(TEST_LAWN.getUpperLimitY());
        sut.setOrientation(Orientation.EAST);

        sut.advance(TEST_LAWN);

        assertEquals(TEST_LAWN.getUpperLimitX(), sut.getPosition().getX());
        assertEquals(TEST_LAWN.getUpperLimitY(), sut.getPosition().getY());
    }

    @Test
    public void testAdvance_toSouth() {
        sut.getPosition().setX(TEST_LAWN.getLowerLimitX());
        sut.getPosition().setY(TEST_LAWN.getLowerLimitY() + 1);
        sut.setOrientation(Orientation.SOUTH);

        sut.advance(TEST_LAWN);

        assertEquals(TEST_LAWN.getLowerLimitX(), sut.getPosition().getX());
        assertEquals(TEST_LAWN.getLowerLimitY(), sut.getPosition().getY());
    }

    @Test
    public void testAdvance_toSouthAtBorder() {
        sut.getPosition().setX(TEST_LAWN.getLowerLimitX());
        sut.getPosition().setY(TEST_LAWN.getLowerLimitY());
        sut.setOrientation(Orientation.SOUTH);

        sut.advance(TEST_LAWN);

        assertEquals(TEST_LAWN.getLowerLimitX(), sut.getPosition().getX());
        assertEquals(TEST_LAWN.getLowerLimitY(), sut.getPosition().getY());
    }

    @Test
    public void testAdvance_toWest() {
        sut.getPosition().setX(TEST_LAWN.getLowerLimitX() + 1);
        sut.getPosition().setY(TEST_LAWN.getLowerLimitY());
        sut.setOrientation(Orientation.WEST);

        sut.advance(TEST_LAWN);

        assertEquals(TEST_LAWN.getLowerLimitX(), sut.getPosition().getX());
        assertEquals(TEST_LAWN.getLowerLimitY(), sut.getPosition().getY());
    }

    @Test
    public void testAdvance_toWestAtBorder() {
        sut.getPosition().setX(TEST_LAWN.getLowerLimitX());
        sut.getPosition().setY(TEST_LAWN.getLowerLimitY());
        sut.setOrientation(Orientation.WEST);

        sut.advance(TEST_LAWN);

        assertEquals(TEST_LAWN.getLowerLimitX(), sut.getPosition().getX());
        assertEquals(TEST_LAWN.getLowerLimitY(), sut.getPosition().getY());
    }

}
