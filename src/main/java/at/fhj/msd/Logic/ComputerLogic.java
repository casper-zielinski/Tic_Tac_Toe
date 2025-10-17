package at.fhj.msd.Logic;

/**
 * Provides the logic for the computer player in Player vs Computer mode.
 * The computer plays as O and uses a strategic approach to make moves.
 */
public class ComputerLogic {

    /**
     * Determines and executes the computer's next move on the game board.
     * The computer follows a priority-based strategy:
     * 1. Win if possible
     * 2. Block player from winning
     * 3. Take center if available
     * 4. Take a corner if available
     * 5. Take any remaining cell
     *
     * @param Grid The current 3x3 game board array to be modified with the computer's move
     */
    public void computerZug(String[][] Grid) {
        // Priority 1: Check if the computer can win with the next move
        int[] zug = findeGewinnendenzug(Grid, "O");
        if (zug != null) {
            Grid[zug[0]][zug[1]] = "O";
            return;
        }

        // Priority 2: Check if the player can win on their next move and block them
        zug = findeGewinnendenzug(Grid, "X");
        if (zug != null) {
            Grid[zug[0]][zug[1]] = "O";
            return;
        }

        // Priority 3: Take the center cell if it is available
        if (Grid[1][1].isEmpty()) {
            Grid[1][1] = "O";
            return;
        }

        // Priority 4: Take an available corner cell
        int[][] ecken = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int[] ecke : ecken) {
            if (Grid[ecke[0]][ecke[1]].isEmpty()) {
                Grid[ecke[0]][ecke[1]] = "O";
                return;
            }
        }

        // Priority 5: Take any remaining empty cell
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Grid[i][j].isEmpty()) {
                    Grid[i][j] = "O";
                    return;
                }
            }
        }
    }

    /**
     * Searches for a move that would result in a win for the specified player.
     * Tests each empty cell by temporarily placing the player's symbol and checking for a win condition.
     *
     * @param Grid The current game board
     * @param spieler The player symbol to check for ("X" or "O")
     * @return An array [row, column] representing the winning move, or null if no winning move exists
     */
    private int[] findeGewinnendenzug(String[][] Grid, String spieler) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Grid[i][j].isEmpty()) {
                    // Temporarily place the player's symbol
                    Grid[i][j] = spieler;

                    // Check if this move would result in a win
                    if (hatGewonnen(Grid, spieler)) {
                        Grid[i][j] = "";  // Reset the cell
                        return new int[]{i, j};
                    }

                    Grid[i][j] = "";  // Reset the cell
                }
            }
        }
        return null;
    }

    /**
     * Checks if the specified player has won the game.
     * Evaluates all rows, columns, and diagonals for three matching symbols.
     *
     * @param Grid The current game board
     * @param spieler The player symbol to check for ("X" or "O")
     * @return true if the player has won, false otherwise
     */
    private boolean hatGewonnen(String[][] Grid, String spieler) {
        // Check all rows for a win
        for (int i = 0; i < 3; i++) {
            if (Grid[i][0].equals(spieler)
                    && Grid[i][1].equals(spieler)
                    && Grid[i][2].equals(spieler)) {
                return true;
            }
        }

        // Check all columns for a win
        for (int j = 0; j < 3; j++) {
            if (Grid[0][j].equals(spieler)
                    && Grid[1][j].equals(spieler)
                    && Grid[2][j].equals(spieler)) {
                return true;
            }
        }

        // Check top-left to bottom-right diagonal
        if (Grid[0][0].equals(spieler)
                && Grid[1][1].equals(spieler)
                && Grid[2][2].equals(spieler)) {
            return true;
        }

        // Check top-right to bottom-left diagonal
        if (Grid[0][2].equals(spieler)
                && Grid[1][1].equals(spieler)
                && Grid[2][0].equals(spieler)) {
            return true;
        }

        return false;
    }

}
