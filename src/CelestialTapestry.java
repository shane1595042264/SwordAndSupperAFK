/*
 * PROGRAM: CelestialTapestry
 * AUTHOR: GPT-5 Codex
 * DATE: 2024-05-12
 * DESCRIPTION: Renders a precise zigzag tapestry using ANSI colors and
 *              unicode symbols while demonstrating structured Java design.
 */
public class CelestialTapestry {

    private static final int TOTAL_ROWS = 40;
    private static final int TOTAL_COLUMNS = 20;
    private static final String ANSI_RESET = "\u001B[0m";
    // Deep navy backdrop that matches the provided reference image.
    private static final String ANSI_NAVY_BACKGROUND = "\u001B[48;5;17m";
    private static final String ANSI_RED_FOREGROUND = "\u001B[31m";
    private static final String ANSI_WHITE_FOREGROUND = "\u001B[97m";
    private static final String ANSI_YELLOW_FOREGROUND = "\u001B[33m";
    private static final char SMILEY_FACE = '\u263A';
    private static final char PADDING_CHAR = '\u2002';

    public static void main(String[] args) {
        launchTapestry();
    }

    /**
     * Starts the rendering process for the tapestry art.
     */
    private static void launchTapestry() {
        renderPattern();
    }

    /**
     * Iterates through each row of the tapestry using an incrementing loop.
     */
    private static void renderPattern() {
        for (int row = 0; row < TOTAL_ROWS; row++) {
            printLine(buildRow(row));
        }
    }

    /**
     * Builds the string representation for a single row of the tapestry.
     */
    private static String buildRow(int row) {
        StringBuilder builder = new StringBuilder();
        builder.append(ANSI_NAVY_BACKGROUND);
        int tokenColumn = locateTokenColumn(row);
        for (int column = 0; column < TOTAL_COLUMNS; column++) {
            builder.append(assembleCell(row, column, tokenColumn));
        }
        builder.append(ANSI_RESET);
        return builder.toString();
    }

    /**
     * Determines which column should display the accent token for a row.
     */
    private static int locateTokenColumn(int row) {
        if (row < 14) {
            return 2 + row;
        } else if (row < 26) {
            return 15 - (row - 14);
        }
        return 2 + (row - 26);
    }

    /**
     * Chooses between the accent token and the blue background cell.
     */
    private static String assembleCell(int row, int column, int tokenColumn) {
        if (column == tokenColumn) {
            return createTokenCell(row);
        }
        return createBackgroundCell();
    }

    /**
     * Produces the blue background cell for empty portions of the tapestry.
     */
    private static String createBackgroundCell() {
        return "  ";
    }

    /**
     * Builds the accent token cell, restoring the background afterward.
     */
    private static String createTokenCell(int row) {
        String smiley = buildSmiley(row);
        return ANSI_RESET + smiley + ANSI_RESET + ANSI_NAVY_BACKGROUND + createPaddingTrail();
    }

    /**
     * Builds the smiley accent using a decrementing loop to prepare layers.
     */
    private static String buildSmiley(int row) {
        String[] palette = new String[] {selectSmileyColor(row), selectAuraColor(row)};
        StringBuilder builder = new StringBuilder();
        for (int index = palette.length - 1; index >= 0; index--) {
            if (index == 0) {
                builder.append(palette[index]).append(SMILEY_FACE);
            } else {
                builder.append(palette[index]);
            }
        }
        return builder.toString();
    }

    /**
     * Chooses the aura color that precedes each smiley.
     */
    private static String selectAuraColor(int row) {
        if (row % 2 == 0) {
            return ANSI_YELLOW_FOREGROUND;
        }
        return "";
    }

    /**
     * Chooses the smiley color, alternating between red and white faces.
     */
    private static String selectSmileyColor(int row) {
        if (row % 2 == 0) {
            return ANSI_RED_FOREGROUND;
        }
        return ANSI_WHITE_FOREGROUND;
    }

    /**
     * Creates a transparent padding trail so the smiley sits on the navy field.
     */
    private static String createPaddingTrail() {
        StringBuilder padding = new StringBuilder();
        for (int pad = 1; pad > 0; pad--) {
            padding.append(PADDING_CHAR);
        }
        return padding.toString();
    }

    /**
     * Prints a fully constructed row to the console.
     */
    private static void printLine(String content) {
        System.out.println(content);
    }
}
