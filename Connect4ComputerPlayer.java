package c4;

public class Connect4ComputerPlayer{

    /**
     *
     * Creates a new Connect4 Computer player
     */
    public Connect4ComputerPlayer() {
    }

    /**
     *
     * creates a new random number for the computer to use
     * @return random integer between 1 and 7.
     */
    public int getRandom() {
        double randomDouble = Math.random();
        randomDouble = randomDouble * 7 + 1;
        int randomInt = (int) randomDouble;
        return randomInt;
    }
}
