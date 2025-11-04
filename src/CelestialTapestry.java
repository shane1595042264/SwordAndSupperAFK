/*
 * PROGRAM: CelestialTapestry
 * AUTHOR: GPT-5 Codex
 * DATE: 2024-05-12
 * DESCRIPTION: Generates a colorful unicode-based tapestry using ANSI colors
 *              and intricate helper methods to demonstrate structured Java code.
 */
public class CelestialTapestry {

    private static final int TOTAL_ROWS = 40;
    private static final int TOTAL_COLUMNS = 36;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    private static final String ANSI_BRIGHT_WHITE_TEXT = "\u001B[97m";
    private static final String ANSI_DARK_TEXT = "\u001B[30m";
    private static final char STAR_SYMBOL = '\u272A';
    private static final char ORBIT_SYMBOL = '\u25CF';

    public static void main(String[] args) {
        runShowcase();
    }

    /**
     * Drives the entire artwork rendering process.
     */
    private static void runShowcase() {
        printProgramTitle();
        printCanvas();
        printDescendingGlow();
    }

    /**
     * Prints a textual introduction framed within the tapestry width.
     */
    private static void printProgramTitle() {
        printRow(createTextRow(""));
        printRow(createTextRow("Sword & Supper AFK presents"));
        printRow(createTextRow("Celestial Supper Stream"));
        printRow(createTextRow(""));
    }

    /**
     * Renders the primary zigzag tapestry using an incrementing loop.
     */
    private static void printCanvas() {
        for (int row = 0; row < TOTAL_ROWS; row++) {
            printRow(buildRow(row));
        }
    }

    /**
     * Constructs a single row of the tapestry by stitching together colored cells.
     */
    private static String buildRow(int rowIndex) {
        StringBuilder builder = new StringBuilder();
        for (int columnIndex = 0; columnIndex < TOTAL_COLUMNS; columnIndex++) {
            builder.append(selectCell(rowIndex, columnIndex));
        }
        builder.append(ANSI_RESET);
        return builder.toString();
    }

    /**
     * Determines which styled cell belongs at the given coordinates.
     */
    private static String selectCell(int rowIndex, int columnIndex) {
        int primaryColumn = computeZigzagColumn(rowIndex, false);
        int secondaryColumn = computeZigzagColumn(rowIndex, true);
        if (columnIndex == primaryColumn) {
            return createSymbolCell(chooseColor(rowIndex, true), chooseSymbol(rowIndex));
        } else if (columnIndex == secondaryColumn) {
            return createSymbolCell(chooseColor(rowIndex, false), chooseSymbol(rowIndex + columnIndex));
        }
        return createBackgroundCell(rowIndex, columnIndex);
    }

    /**
     * Calculates the zigzag path column for either side of the pattern.
     */
    private static int computeZigzagColumn(int rowIndex, boolean mirrored) {
        int segmentLength = 10;
        int period = segmentLength * 2;
        int progress = rowIndex % period;
        int offset = progress < segmentLength
                ? progress
                : segmentLength - (progress - segmentLength) - 1;
        offset = Math.max(offset, 0);
        int baseColumn = 4;
        if (mirrored) {
            return (TOTAL_COLUMNS - baseColumn - 1) - offset;
        }
        return baseColumn + offset;
    }

    /**
     * Chooses which symbol should appear for a particular row.
     */
    private static char chooseSymbol(int seed) {
        if (seed % 3 == 0) {
            return STAR_SYMBOL;
        }
        return ORBIT_SYMBOL;
    }

    /**
     * Picks a background color for the moving symbols.
     */
    private static String chooseColor(int rowIndex, boolean primaryPath) {
        int phase = rowIndex % 4;
        if (primaryPath) {
            return phase < 2 ? ANSI_RED_BACKGROUND : ANSI_CYAN_BACKGROUND;
        }
        return phase < 2 ? ANSI_CYAN_BACKGROUND : ANSI_WHITE_BACKGROUND;
    }

    /**
     * Builds the shimmering background cell for non-symbol spaces.
     */
    private static String createBackgroundCell(int rowIndex, int columnIndex) {
        boolean highlight = (rowIndex + columnIndex) % 7 == 0;
        String color = highlight ? ANSI_CYAN_BACKGROUND : ANSI_BLUE_BACKGROUND;
        return color + " " + ANSI_RESET;
    }

    /**
     * Generates a colored symbol cell with appropriate foreground contrast.
     */
    private static String createSymbolCell(String backgroundColor, char symbol) {
        String textColor = backgroundColor.equals(ANSI_WHITE_BACKGROUND) ? ANSI_DARK_TEXT : ANSI_BRIGHT_WHITE_TEXT;
        return backgroundColor + textColor + symbol + ANSI_RESET;
    }

    /**
     * Wraps text inside a full-width, colored banner row.
     */
    private static String createTextRow(String message) {
        String centered = centerText(message);
        return ANSI_BLUE_BACKGROUND + ANSI_BRIGHT_WHITE_TEXT + centered + ANSI_RESET;
    }

    /**
     * Centers provided text according to the tapestry width.
     */
    private static String centerText(String text) {
        String trimmed = text.length() > TOTAL_COLUMNS ? text.substring(0, TOTAL_COLUMNS) : text;
        int totalPadding = TOTAL_COLUMNS - trimmed.length();
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;
        return repeatChar(' ', leftPadding) + trimmed + repeatChar(' ', rightPadding);
    }

    /**
     * Repeats a character a specified number of times.
     */
    private static String repeatChar(char ch, int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(ch);
        }
        return builder.toString();
    }

    /**
     * Prints a single line of output without repeating println statements elsewhere.
     */
    private static void printRow(String rowContent) {
        System.out.println(rowContent);
    }

    /**
     * Adds a descending glow trail using a decrementing loop for contrast.
     */
    private static void printDescendingGlow() {
        for (int width = TOTAL_COLUMNS; width >= 10; width -= 5) {
            printRow(createGlowLine(width));
        }
    }

    /**
     * Builds one shimmering glow line composed of alternating symbols.
     */
    private static String createGlowLine(int width) {
        int safeWidth = Math.max(2, Math.min(width, TOTAL_COLUMNS));
        int padding = (TOTAL_COLUMNS - safeWidth) / 2;
        StringBuilder builder = new StringBuilder();
        for (int columnIndex = 0; columnIndex < TOTAL_COLUMNS; columnIndex++) {
            boolean insideGlow = columnIndex >= padding && columnIndex < padding + safeWidth;
            if (insideGlow) {
                boolean inverted = (columnIndex - padding) % 2 == 0;
                String color = inverted ? ANSI_RED_BACKGROUND : ANSI_CYAN_BACKGROUND;
                char symbol = inverted ? STAR_SYMBOL : ORBIT_SYMBOL;
                builder.append(createSymbolCell(color, symbol));
            } else {
                builder.append(createBackgroundCell(TOTAL_ROWS + width, columnIndex));
            }
        }
        builder.append(ANSI_RESET);
        return builder.toString();
    }
}
