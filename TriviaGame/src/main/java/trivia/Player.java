// src/main/java/trivia/Player.java
package trivia;

public class Player {
    private final String name;
    private int position = 0;
    private int purse = 0;
    private boolean inPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getPurse() {
        return purse;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void move(int roll, int boardSize) {
        position = (position + roll) % boardSize;
    }

    public void incrementPurse() {
        purse++;
    }

    public void sendToPenaltyBox() {
        inPenaltyBox = true;
    }

    public boolean hasWon(int winningScore) {
        return purse == winningScore;
    }
}