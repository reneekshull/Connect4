package c4;
import java.util.Scanner;

public class Connect4TextConsole {

    public static void main(String[] args) {
        c4.Connect4 game = new c4.Connect4();

        Scanner in = new Scanner(System.in);
        char activePlayer = 'X';


        System.out.println("Enter ‘P’ if you want to play against another player; Enter ‘C’ to play against computer.");
        // TO DO : add error checking for other values (not P or C)

        char gameType = in.next().charAt(0);

        System.out.println("Enter ‘G’ if you want the GUI interface ; Enter ‘T’ to use the Text interface.");

        game.displayType(in.next().charAt(0));
        new c4.Connect4Server().main(args);


        if (gameType == 'P') {
            System.out.println("Start game against friend");

            do {

                game.turn(game, activePlayer, gameType);

                if(activePlayer == 'X'){
                    activePlayer = 'O';
                } else { activePlayer = 'X'; }


                System.out.println();

            } while (!game.isOver() && !game.checkWin());

        } else if (gameType == 'C') {
            System.out.println("Start game against computer");

        }

        if (game.checkWin()) {
            System.out.printf("Player " + activePlayer + " won the game.");
        } else {
            System.out.println("Game is over.");
        }
        in.close();
    }
}