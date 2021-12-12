package com.mowitnow.exceptions;

/**
 * Exception thrown when an error occurs while reading configuration file.
 */
public class CorruptedLawnmowerConfigurationException extends Exception {

    /**
     * Constructor.
     * @param corruptedConfiguration The corrupted configuration.
     */
    public CorruptedLawnmowerConfigurationException(final String corruptedConfiguration) {
        super("Cannot build lawnmower, corrupted configuration: " + corruptedConfiguration);
    }

}
