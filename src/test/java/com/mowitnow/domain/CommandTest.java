package com.mowitnow.domain;

import org.junit.jupiter.api.Test;

import static com.mowitnow.domain.Command.fromNotation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link Command}.
 */
class CommandTest {

    @Test
    public void testGetNotation() {
        assertEquals('D', Command.TURN_RIGHT.getNotation());
        assertEquals('G', Command.TURN_LEFT.getNotation());
        assertEquals('A', Command.ADVANCE.getNotation());
    }

    @Test
    public void testFromNotation() {
        assertEquals(Command.TURN_RIGHT, fromNotation('D').orElse(null));
        assertEquals(Command.TURN_LEFT, fromNotation('G').orElse(null));
        assertEquals(Command.ADVANCE, fromNotation('A').orElse(null));
        assertTrue(fromNotation('B').isEmpty());
    }

}
