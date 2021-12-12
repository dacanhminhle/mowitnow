package com.mowitnow.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link MowingConfiguration}.
 */
class MowingConfigurationTest {

    @Test
    public void testPojo() {
        final Point initialPoint = new Point(2, 3);
        final Orientation initialOrientation = Orientation.NORTH;
        final List<Command> commandList = Collections.singletonList(Command.ADVANCE);
        final MowingConfiguration sut = new MowingConfiguration(initialPoint, initialOrientation, commandList);

        assertEquals(initialPoint, sut.getInitialPoint());
        assertEquals(initialOrientation, sut.getInitialOrientation());
        assertEquals(commandList, sut.getCommandList());
    }

}
