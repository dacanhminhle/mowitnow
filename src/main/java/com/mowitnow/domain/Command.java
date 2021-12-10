package com.mowitnow.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Enumeration for different applicable commands.
 */
public enum Command {

    /** Turn right command. */
    TURN_RIGHT('D'),
    /** Turn left command. */
    TURN_LEFT('G'),
    /** Advance command. */
    ADVANCE('A');

    /** Map between notations and the corresponding commands. */
    private static final Map<Character, Command> NOTATION_MAP = new HashMap<>();

    static {
        for (final Command command : values()) {
            NOTATION_MAP.put(command.getNotation(), command);
        }
    }

    /** The notation for the command (must be a character). */
    private final char notation;

    /**
     * Constructor.
     * @param notation See {@link Command#notation}.
     */
    Command(final char notation) {
        this.notation = notation;
    }

    /**
     * @return {@link Command#notation}.
     */
    public char getNotation() {
        return notation;
    }

    /**
     * Retrieves the corresponding {@link Command} of a given notation.
     * @param notation The given notation.
     * @return The corresponding {@link Command}.
     */
    public static Optional<Command> fromNotation(final char notation) {
        return Optional.ofNullable(NOTATION_MAP.get(notation));
    }

}
