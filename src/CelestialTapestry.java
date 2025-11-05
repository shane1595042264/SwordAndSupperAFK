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
            return createTokenCell(row, true);
        } else if (column == tokenColumn + 1) {
            return createTokenCell(row, false);
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
    private static String createTokenCell(int row, boolean isLeadingSmiley) {
        String smileyColor = selectSmileyColor(row, isLeadingSmiley);
        String smiley = buildSmiley(smileyColor);
        StringBuilder cell = new StringBuilder();
        cell.append(ANSI_RESET).append(smiley).append(ANSI_RESET).append(ANSI_NAVY_BACKGROUND);
        if (!isLeadingSmiley) {
            cell.append(PADDING_CHAR);
        }
        return cell.toString();
    }

    /**
     * Builds the smiley accent using a decrementing loop to prepare layers.
     */
    private static String buildSmiley(String smileyColor) {
        StringBuilder builder = new StringBuilder();
        for (int layer = 1; layer >= 0; layer--) {
            if (layer == 1) {
                builder.append(smileyColor);
            } else {
                builder.append(SMILEY_FACE);
            }
        }
        return builder.toString();
    }

    /**
     * Chooses the smiley color, alternating between red and white faces.
     */
    private static String selectSmileyColor(int row, boolean isLeadingSmiley) {
        boolean evenRow = row % 2 == 0;
        if (isLeadingSmiley) {
            return evenRow ? ANSI_WHITE_FOREGROUND : ANSI_RED_FOREGROUND;
        }
        return evenRow ? ANSI_RED_FOREGROUND : ANSI_WHITE_FOREGROUND;
    }

    /**
     * Prints a fully constructed row to the console.
     */
    private static void printLine(String content) {
        System.out.println(content);
    }
}
