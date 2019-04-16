package c4;
import java.util.Scanner;

// ConnectFour game
public class Connect4 {

    char[][] b;

    /**
     *
     * Creates a new Connect4 game through an array of the gameboard size
     */
    public Connect4() {
        b = new char[6][7];

        for(int i=0; i<b.length; i++) {
            for(int j=0; j<b[i].length; j++) {
                b[i][j] = ' ';
            }
        }
    }

    /**
     *
     * takes in the display type and opens the GUI application if requested
     *
     * @param displayType a char (either G or T)
     *
     */
    public void displayType(char displayType){
        if(displayType == 'G'){
            javafx.application.Application.launch(Connect4GUI.class);
        }
    }



    /**
     *
     * takes in the display type and opens the GUI application if requested
     *
     * @param gameType a char (either G or T)
     *
     */
    public void gameType(char gameType){
        if(gameType == 'C'){
            c4.Connect4ComputerPlayer computer = new c4.Connect4ComputerPlayer();
        }
    }
    /**
     * Creates a new turn function
     *
     * @return the char value of the nextPlayer
     * @param game a connect4 game
     * @param activePlayer a char
     * @param gameType a char
     */
    public static char turn(Connect4 game, char activePlayer, char gameType) {
        game.printBoard();
        char nextPlayer;


        if (activePlayer == 'O') {
            nextPlayer = 'X';
        } else {
            nextPlayer = 'O';
        }

        System.out.print("Player" + activePlayer + "- your turn. Choose a column (1-7): ");

        Scanner in = new Scanner(System.in);

        boolean status = false;
        while (!status) {
            try {

                if (gameType == 'P') {
                    status = game.drop(activePlayer, in.nextInt());

                } else if (gameType == 'C') {
                    if (activePlayer == 'X') {
                        status = game.drop(activePlayer, in.nextInt());

                    } else if (activePlayer == 'O') {
                        c4.Connect4ComputerPlayer computer = new c4.Connect4ComputerPlayer(); //TO DO create @ beginning of game
                        int number = computer.getRandom();
                        status = game.drop(activePlayer, number);
                    }
                }

                if (!status) {
                    System.out.println("Could not play. Invalid position or Position is already filled.");
                    System.out.print("Try Again: ");
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
                System.out.print("Try Again: ");
                in.nextLine(); // clear buffer
            }
        }
        System.out.println();

        return nextPlayer;
    }

    /**
     *
     * Checks if the turn is over
     * @return boolean value if turn is over or not
     */
    boolean isOver() {
        for(int i=0; i<b[0].length; i++) {
            if(b[0][i] == ' ') {
                // this place is vacant
                return false;
            }
        }
        return true;
    }

    /**
     *
     * Prints the connect 4 board
     */
    public void printBoard() {

        for(int i=0; i<b.length; i++) {
            System.out.printf("|");

            for(int j=0; j<b[i].length; j++) {
                System.out.printf("%c|", b[i][j]);
            }
            System.out.println();
        }

        for(int j=0; j<b[0].length; j++) {
            System.out.printf("--");
        }
        System.out.println("-");
    }

    /**
     *
     * Method to check for a winner.
     * @return boolean value of whether there is a winner
     */
    public boolean checkWin() {
        // Create four boolean variables, one for each set of rows. Initialize all of
        // them to false.
        boolean foundRow = false;
        boolean foundCol = false;
        boolean foundMjrD = false;
        boolean foundMinD = false;

        // Check to see if four consecutive cells in a row match.
        // check rows
        for (int r = 0; r <= 5; r++) {
            for (int c = 0; c <= 3; c++) {
                if (b[r][c] == b[r][c + 1] && b[r][c] == b[r][c + 2] && b[r][c] == b[r][c + 3] && b[r][c] != ' ') {
                    foundRow = true;
                    break;
                }
            }
        }

        // Check to see if four columns in the same row match
        // check columns
        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 6; c++) {
                if (b[r][c] == b[r + 1][c] && b[r][c] == b[r + 2][c] && b[r][c] == b[r + 3][c] && b[r][c] != ' ') {
                    foundCol = true;
                    break;
                }
            }
        }

        // Check to see if four diagonals match (top left to bottom right)
        // check major diagonal
        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 3; c++) {
                if (b[r][c] == b[r + 1][c + 1] && b[r][c] == b[r + 2][c + 2] && b[r][c] == b[r + 3][c + 3]
                        && b[r][c] != ' ') {
                    foundMjrD = true;
                    break;
                }
            }
        }

        // Check to see if four diagonals in the other direction match (top right to
        // bottom left)
        // check minor diagonal
        for (int r = 0; r <= 2; r++) {
            for (int c = 3; c <= 6; c++) {
                if (b[r][c] == b[r + 1][c - 1] && b[r][c] == b[r + 2][c - 2] && b[r][c] == b[r + 3][c - 3]
                        && b[r][c] != ' ') {
                    foundMinD = true;
                    break;
                }
            }
        }

        // If ONE of the booleans is true, we have a winner.
        // checks boolean for a true
        if (foundRow || foundCol || foundMjrD || foundMinD) {
            return true;
        } else { return false; }
    } // end checkWin method

    /**
     *
     * Method to drop the disk in the given column space, if it is available.
     * @param player (0 or 1)
     * @param column number
     * @return boolean value of whether game piece is dropped into given column or not.
     */
    public boolean drop(char player, int column) {
        column--; // to get on 0 index base
        if(column < 0 || column > 6) {
            throw new ArrayIndexOutOfBoundsException("There is no mentioned column to drop the disk.");
        }
        boolean put = false;
        for(int i=b.length - 1; i >= 0; i--) {
            if(b[i][column] == ' ') {
                b[i][column] = player;
                put = true;
                break;
            }
        }

        return put;
    }

}