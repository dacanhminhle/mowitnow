package com.mowitnow.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Lawnmower's mowing configuration, including its initial point and orientation, as well as the list of {@link Command} to execute.
 */
public class MowingConfiguration {

    /** Initial point where lawnmower will start mowing. */
    private final Point initialPoint;
    /** Lawnmower's initial orientation. */
    private final Orientation initialOrientation;
    /** Lawnmower's list of {@link Command} to execute. */
    private final List<Command> commandList;

    /**
     * Constructor.
     * @param initialPoint       See {@link MowingConfiguration#initialPoint}.
     * @param initialOrientation See {@link MowingConfiguration#initialOrientation}.
     * @param commandList        See {@link MowingConfiguration#commandList}.
     */
    public MowingConfiguration(final Point initialPoint, final Orientation initialOrientation, final List<Command> commandList) {
        this.initialPoint = Objects.requireNonNull(initialPoint, "Requires a non null Point");
        this.initialOrientation = Objects.requireNonNull(initialOrientation, "Requires a non null Orientation");
        this.commandList = Objects.requireNonNull(commandList, "Requires a non null List of Command");
    }

    /**
     * @return {@link MowingConfiguration#initialPoint}.
     */
    public Point getInitialPoint() {
        return initialPoint;
    }

    /**
     * @return {@link MowingConfiguration#initialOrientation}.
     */
    public Orientation getInitialOrientation() {
        return initialOrientation;
    }

    /**
     * @return {@link MowingConfiguration#commandList}.
     */
    public List<Command> getCommandList() {
        return commandList;
    }

    @Override
    public String toString() {
        return "MowingConfiguration{"
                + "(" + initialPoint.getX() + ", " + initialPoint.getY() + ", " + initialOrientation.getNotation() + ")"
                + "; commandList = \"" + commandList.stream().map(Command::getNotation).map(Object::toString).collect(Collectors.joining()) + "\""
                + '}';
    }

}
