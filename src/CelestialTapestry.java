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
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    private static final String ANSI_RED_FOREGROUND = "\u001B[31m";
    private static final String ANSI_WHITE_FOREGROUND = "\u001B[97m";
    private static final String ANSI_GRAY_FOREGROUND = "\u001B[37m";
    private static final char OUTER_RING = '\u25CE';
    private static final char INNER_GLOW = '\u25CF';

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
        builder.append(ANSI_BLUE_BACKGROUND);
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
        return ANSI_RESET + applyTokenLayers(row) + ANSI_RESET + ANSI_BLUE_BACKGROUND;
    }

    /**
     * Uses a decrementing loop to assemble the layered unicode symbols.
     */
    private static String applyTokenLayers(int row) {
        String[] palette = new String[] {selectInnerColor(row), selectOuterColor(row)};
        StringBuilder layerBuilder = new StringBuilder();
        for (int index = palette.length - 1; index >= 0; index--) {
            if (index == palette.length - 1) {
                layerBuilder.append(palette[index]).append(OUTER_RING);
            } else {
                layerBuilder.append(palette[index]).append(INNER_GLOW);
            }
        }
        return layerBuilder.toString();
    }

    /**
     * Chooses the outer ring color based on the row number.
     */
    private static String selectOuterColor(int row) {
        if (row % 2 == 0) {
            return ANSI_RED_FOREGROUND;
        }
        return ANSI_WHITE_FOREGROUND;
    }

    /**
     * Chooses the inner glow color to contrast with the outer ring.
     */
    private static String selectInnerColor(int row) {
        if (row % 2 == 0) {
            return ANSI_WHITE_FOREGROUND;
        }
        return ANSI_GRAY_FOREGROUND;
    }

    /**
     * Prints a fully constructed row to the console.
     */
    private static void printLine(String content) {
        System.out.println(content);
    }
}
