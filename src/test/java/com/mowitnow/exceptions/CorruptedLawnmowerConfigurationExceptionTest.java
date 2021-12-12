package com.mowitnow.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link CorruptedLawnmowerConfigurationException}.
 */
class CorruptedLawnmowerConfigurationExceptionTest {

    @Test
    public void testConstructor() {
        final String corruptedConfig = "test";
        final CorruptedLawnmowerConfigurationException sut = new CorruptedLawnmowerConfigurationException(corruptedConfig);

        assertEquals("Cannot build lawnmower, corrupted configuration: " + corruptedConfig, sut.getMessage());
    }

}
