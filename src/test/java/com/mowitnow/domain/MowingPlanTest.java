package com.mowitnow.domain;

import com.mowitnow.exceptions.CorruptedLawnmowerConfigurationException;
import com.mowitnow.exceptions.UnknownCommandException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link MowingPlan}.
 */
class MowingPlanTest {

    private MowingPlan sut;

    @Test
    public void testConstructor() throws CorruptedLawnmowerConfigurationException {
        sut = new MowingPlan("src/main/resources/test");

        assertEquals(5, sut.getLawn().getUpperLimitX());
        assertEquals(5, sut.getLawn().getUpperLimitY());
        assertEquals(2, sut.getLawnmowers().size());

        final Lawnmower lawnmower1 = sut.getLawnmowers().get(0);
        final Lawnmower lawnmower2 = sut.getLawnmowers().get(1);

        assertEquals(1, lawnmower1.getMowingConfiguration().getInitialPoint().getX());
        assertEquals(2, lawnmower1.getMowingConfiguration().getInitialPoint().getY());
        assertEquals(Orientation.NORTH, lawnmower1.getMowingConfiguration().getInitialOrientation());
        assertEquals(buildCommandList("GAGAGAGAA"), lawnmower1.getMowingConfiguration().getCommandList());

        assertEquals(3, lawnmower2.getMowingConfiguration().getInitialPoint().getX());
        assertEquals(3, lawnmower2.getMowingConfiguration().getInitialPoint().getY());
        assertEquals(Orientation.EAST, lawnmower2.getMowingConfiguration().getInitialOrientation());
        assertEquals(buildCommandList("AADAADADDA"), lawnmower2.getMowingConfiguration().getCommandList());
    }

    @Test
    public void testConstructor_withInexistentConfigurationFile() throws CorruptedLawnmowerConfigurationException {
        sut = new MowingPlan("");

        assertNull(sut.getLawn());
        assertTrue(sut.getLawnmowers().isEmpty());
    }

    @Test
    public void testConstructor_withBlankFile() throws CorruptedLawnmowerConfigurationException {
        sut = new MowingPlan("src/test/resources/test_blank_file");

        assertNull(sut.getLawn());
        assertTrue(sut.getLawnmowers().isEmpty());
    }

    @Test
    public void testConstructor_withFileHavingLawnConfigurationOnly() throws CorruptedLawnmowerConfigurationException {
        sut = new MowingPlan("src/test/resources/test_file_only_lawn");

        assertEquals(5, sut.getLawn().getUpperLimitX());
        assertEquals(5, sut.getLawn().getUpperLimitY());
        assertTrue(sut.getLawnmowers().isEmpty());
    }

    @Test
    public void testConstructor_withFileHavingCorruptedConfiguration() {
        assertThrows(
                CorruptedLawnmowerConfigurationException.class,
                () -> new MowingPlan("src/test/resources/test_file_corrupted")
        );
    }

    @Test
    public void testConstructor_withFileMissingCommands() throws CorruptedLawnmowerConfigurationException {
        sut = new MowingPlan("src/test/resources/test_file_missing_commands");

        assertEquals(5, sut.getLawn().getUpperLimitX());
        assertEquals(5, sut.getLawn().getUpperLimitY());
        assertEquals(1, sut.getLawnmowers().size());

        final Lawnmower lawnmower = sut.getLawnmowers().get(0);

        assertEquals(1, lawnmower.getMowingConfiguration().getInitialPoint().getX());
        assertEquals(2, lawnmower.getMowingConfiguration().getInitialPoint().getY());
        assertEquals(Orientation.NORTH, lawnmower.getMowingConfiguration().getInitialOrientation());
        assertTrue(lawnmower.getMowingConfiguration().getCommandList().isEmpty());
    }

    @Test
    public void testExecute() throws CorruptedLawnmowerConfigurationException {
        sut = new MowingPlan("src/main/resources/test");
        final Lawnmower lawnmower1 = sut.getLawnmowers().get(0);
        final Lawnmower lawnmower2 = sut.getLawnmowers().get(1);

        sut.execute();

        assertEquals(1, lawnmower1.getPosition().getX());
        assertEquals(3, lawnmower1.getPosition().getY());
        assertEquals(Orientation.NORTH, lawnmower1.getOrientation());

        assertEquals(5, lawnmower2.getPosition().getX());
        assertEquals(1, lawnmower2.getPosition().getY());
        assertEquals(Orientation.EAST, lawnmower2.getOrientation());
    }

    /**
     * Builds a command list providing a String of command notations.
     * @param commandListStr The provided String of command notations.
     * @return The built command list.
     */
    private List<Command> buildCommandList(final String commandListStr) {
        return commandListStr.trim().chars()
                .mapToObj(c -> (char) c)
                .map(c -> Command.fromNotation(c).orElseThrow(() -> new UnknownCommandException(c)))
                .collect(Collectors.toList());
    }

}
