package com.mowitnow.domain;

import com.mowitnow.exceptions.UnknownCommandException;
import com.mowitnow.exceptions.UnknownOrientationException;

import java.util.Objects;

/**
 * Lawnmower for mowing a {@link Lawn} by navigating within the latter's grid of points.
 */
public class Lawnmower {

    /** Lawnmower's mowing configuration. */
    private MowingConfiguration mowingConfiguration;
    /** Lawnmower's current position. */
    private final Position position;
    /** Lawnmower's current orientation. */
    private Orientation orientation;

    /**
     * Constructor.
     * @param mowingConfiguration See {@link Lawnmower#mowingConfiguration}.
     */
    public Lawnmower(final MowingConfiguration mowingConfiguration) {
        setMowingConfiguration(mowingConfiguration);
        this.position = new Position(Objects.requireNonNull(mowingConfiguration.getInitialPoint(), "Requires a non null Point"));
        setOrientation(mowingConfiguration.getInitialOrientation());
    }

    /**
     * @return {@link Lawnmower#mowingConfiguration}.
     */
    public MowingConfiguration getMowingConfiguration() {
        return mowingConfiguration;
    }

    /**
     * @return {@link Lawnmower#position}.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @return {@link Lawnmower#orientation}.
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * @param mowingConfiguration See {@link Lawnmower#mowingConfiguration}.
     */
    public void setMowingConfiguration(final MowingConfiguration mowingConfiguration) {
        this.mowingConfiguration = Objects.requireNonNull(mowingConfiguration, "Requires a non null MowingConfiguration");
    }

    /**
     * @param orientation The provided new orientation.
     */
    public void setOrientation(final Orientation orientation) {
        this.orientation = Objects.requireNonNull(orientation, "Requires a non null Orientation");
    }

    /**
     * Mows the given {@link Lawn}, applying the list of {@link Command}.
     * @param lawn The given {@link Lawn}.
     */
    public void mow(final Lawn lawn) {
        for (final Command command : mowingConfiguration.getCommandList()) {
            switch (command) {
                case TURN_RIGHT:
                    turnRight();
                    break;
                case TURN_LEFT:
                    turnLeft();
                    break;
                case ADVANCE:
                    advance(lawn);
                    break;
                default:
                    throw new UnknownCommandException(command);
            }
        }
    }

    /**
     * Turns right, without advancing.
     */
    public void turnRight() {
        setOrientation(orientation.getRightOrientation());
    }

    /**
     * Turns left, without advancing.
     */
    public void turnLeft() {
        setOrientation(orientation.getLeftOrientation());
    }

    /**
     * Advances within the given {@link Lawn} in the current {@link Orientation} by one point (and mowing it).
     * @param lawn The given {@link Lawn}.
     */
    public void advance(final Lawn lawn) {
        int currentX = position.getX();
        int currentY = position.getY();

        switch (orientation) {
            case NORTH:
                if (currentY < lawn.getUpperLimitY()) {
                    currentY++;
                    position.setY(currentY);
                }
                break;
            case EAST:
                if (currentX < lawn.getUpperLimitX()) {
                    currentX++;
                    position.setX(currentX);
                }
                break;
            case SOUTH:
                if (currentY > lawn.getLowerLimitY()) {
                    currentY--;
                    position.setY(currentY);
                }
                break;
            case WEST:
                if (currentX > lawn.getLowerLimitX()) {
                    currentX--;
                    position.setX(currentX);
                }
                break;
            default:
                throw new UnknownOrientationException(orientation);
        }
    }

    @Override
    public String toString() {
        return "Lawnmower{"
                + "initConfig = " + mowingConfiguration
                + '}';
    }

    /**
     * @return The current {@link Position} and {@link Orientation} of the lawnmower in String.
     */
    public String toStringCurrent() {
        return "currently (" + position.getX() + ", " + position.getY() + ", " + orientation.getNotation() + ")";
    }

}
