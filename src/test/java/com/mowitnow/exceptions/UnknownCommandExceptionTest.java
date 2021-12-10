package com.mowitnow.exceptions;

import com.mowitnow.domain.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link UnknownCommandException}.
 */
class UnknownCommandExceptionTest {

    private UnknownCommandException sut;

    @Test
    public void testConstructorWithCommand() {
        final Command command = Command.ADVANCE;
        sut = new UnknownCommandException(command);

        assertEquals("Unknown command: " + command, sut.getMessage());
    }

    @Test
    public void testConstructorWithNotation() {
        final char notation = 'A';
        sut = new UnknownCommandException(notation);

        assertEquals("Unknown command, with the following notation: " + notation, sut.getMessage());
    }

}
