package com.mowitnow.domain;

import com.mowitnow.exceptions.CorruptedLawnmowerConfigurationException;
import com.mowitnow.exceptions.UnknownCommandException;
import com.mowitnow.exceptions.UnknownOrientationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Mowing plan describing a lawn and the lawnmowers that will mow it.
 */
public class MowingPlan {

    /** Class logger. */
    private static final Logger LOGGER = LogManager.getLogger(MowingPlan.class);

    /** The lawn to be mowed. */
    private Lawn lawn;
    /** The lawnmowers that will mow the lawn. */
    private List<Lawnmower> lawnmowers = new ArrayList<>();

    /**
     * Constructor.
     * @param filePath The provided path to the configuration file.
     * @throws CorruptedLawnmowerConfigurationException Exception thrown when an error occurs while reading the configuration file.
     */
    public MowingPlan(final String filePath) throws CorruptedLawnmowerConfigurationException {
        final List<String> fileContent = readFile(filePath);

        if (fileContent == null) {
        } else if (fileContent.isEmpty()) {
            LOGGER.warn("Mowing plan file has no content.");
        } else {
            setLawn(buildLawn(fileContent.get(0)));
            setLawnmowers(buildLawnmowers(fileContent));
        }

        LOGGER.info("Configuration file reading complete!");
    }

    /**
     * Reads the configuration file having the provided path.
     * @param filePath The provided path to the configuration file.
     * @return The configuration read line by line, or {@code null} if an error occurs while reading the file.
     */
    private List<String> readFile(final String filePath) {
        try {
            LOGGER.info("Reading configuration file \"" + filePath + "\"...");
            return Files.lines(Paths.get(filePath))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Error while reading mowing plan file \"" + filePath + "\", no mowing plan imported.", e);
            return null;
        }
    }

    /**
     * Builds a lawn with the provided configuration line.
     * @param line The provided configuration line.
     * @return The corresponding built lawn.
     */
    private Lawn buildLawn(final String line) {
        final String[] upperLimitPointCoords = line.split(" ");
        final int upperLimitX = Integer.parseInt(upperLimitPointCoords[0]);
        final int upperLimitY = Integer.parseInt(upperLimitPointCoords[1]);

        return new Lawn(upperLimitX, upperLimitY);
    }

    /**
     * Builds the lawnmowers with the configuration file's content.
     * @param fileContent The configuration file's content.
     * @return The corresponding built lawnmowers.
     * @throws CorruptedLawnmowerConfigurationException Exception thrown when an error occurs while reading the configuration file.
     */
    private List<Lawnmower> buildLawnmowers(final List<String> fileContent) throws CorruptedLawnmowerConfigurationException {
        final List<Lawnmower> lawnmowers = new ArrayList<>();
        final int linesCount = fileContent.size();

        if (linesCount == 1) {
            LOGGER.warn("This mowing plan file contains only lawn information, nothing about lawnmowers' configuration.");
        }

        int x;
        int y;
        Orientation orientation;
        List<Command> commandList;

        for (int i = 1; i < linesCount; i = i + 2) {
            final String[] configInfo = fileContent.get(i).split(" ");

            if (configInfo.length != 3) {
                throw new CorruptedLawnmowerConfigurationException(fileContent.get(i));
            }

            x = Integer.parseInt(configInfo[0]);
            y = Integer.parseInt(configInfo[1]);
            orientation = Orientation.fromNotation(configInfo[2]).orElseThrow(() -> new UnknownOrientationException(configInfo[2]));

            if (i == linesCount - 1) {
                LOGGER.warn("A lawnmower without any commands will be built.");
                commandList = new ArrayList<>();
            } else {
                commandList = fileContent.get(i + 1).trim().chars()
                        .mapToObj(c -> (char) c)
                        .map(c -> Command.fromNotation(c).orElseThrow(() -> new UnknownCommandException(c)))
                        .collect(Collectors.toList());
            }

            final MowingConfiguration configuration = new MowingConfiguration(new Point(x, y), orientation, commandList);
            final Lawnmower lawnmower = new Lawnmower(configuration);
            lawnmowers.add(lawnmower);
        }

        return lawnmowers;
    }

    /**
     * @return {@link MowingPlan#lawn}.
     */
    public Lawn getLawn() {
        return lawn;
    }

    /**
     * @return {@link MowingPlan#lawnmowers}.
     */
    public List<Lawnmower> getLawnmowers() {
        return lawnmowers;
    }

    /**
     * @param lawn See {@link MowingPlan#lawn}.
     */
    public void setLawn(final Lawn lawn) {
        this.lawn = Objects.requireNonNull(lawn, "Requires a non null Lawn");
    }

    /**
     * @param lawnmowers See {@link MowingPlan#lawnmowers}.
     */
    public void setLawnmowers(final List<Lawnmower> lawnmowers) {
        this.lawnmowers = Objects.requireNonNull(lawnmowers, "Requires a non null List of Lawnmower");
    }

    /**
     * Executes the mowing plan, for each lawnmower.
     */
    public void execute() {
        LOGGER.info("Executing mowing plan...");

        for (int i = 0; i < lawnmowers.size(); i++) {
            final Lawnmower lawnmower = lawnmowers.get(i);
            lawnmower.mow(lawn);
            LOGGER.info("Lawnmower " + (i + 1) + ": " + lawnmower.toStringCurrent());
        }

        LOGGER.info("Mowing plan execution complete!");
    }

    @Override
    public String toString() {
        return "MowingPlan {\n"
                + "Lawn: " + lawn + "\n"
                + "Lawnmowers: [\n" + lawnmowers.stream().map(Lawnmower::toString).collect(Collectors.joining("\n")) + "\n]\n"
                + '}';
    }

}
