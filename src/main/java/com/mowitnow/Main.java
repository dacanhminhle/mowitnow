package com.mowitnow;

import com.mowitnow.domain.MowingPlan;
import com.mowitnow.exceptions.CorruptedLawnmowerConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class of the application.
 */
public class Main {

    /** Class logger. */
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * Main method.
     * @param args The provided arguments.
     * @throws CorruptedLawnmowerConfigurationException Exception thrown when an error occurs while reading configuration file.
     */
    public static void main(final String[] args) throws CorruptedLawnmowerConfigurationException {
        final String filePath = "src/main/resources/test";
        final MowingPlan mowingPlan = new MowingPlan(filePath);
        LOGGER.info(mowingPlan);

        mowingPlan.execute();
    }

}
