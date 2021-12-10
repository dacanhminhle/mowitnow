package com.mowitnow.exceptions;

import com.mowitnow.domain.Command;

/**
 * Exception thrown when encountering an unknown {@link Command}.
 */
public class UnknownCommandException extends RuntimeException {

    /**
     * Constructor providing a {@link Command}.
     * @param command The given {@link Command}.
     */
    public UnknownCommandException(final Command command) {
        super("Unknown command: " + command);
    }

    /**
     * Constructor providing a char notation.
     * @param notation The given notation.
     */
    public UnknownCommandException(final char notation) {
        super("Unknown command, with the following notation: " + notation);
    }

}
