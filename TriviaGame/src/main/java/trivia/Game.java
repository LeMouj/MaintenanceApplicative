// src/main/java/trivia/Game.java
package trivia;

import java.util.ArrayList;
import java.util.List;

public class Game implements IGame {
   private static final int MAX_PLAYERS = 6;
   private static final int WINNING_SCORE = 6;
   private static final int BOARD_SIZE = 12;

   private final List<Player> players = new ArrayList<>();
   private final QuestionDeck questionDeck = new QuestionDeck();
   private int currentPlayerIndex = 0;
   private boolean isGettingOutOfPenaltyBox;

   private final List<String> categories = List.of("Pop", "Science", "Sports", "Geography", "Rock");

   public boolean add(String playerName) {
      if (players.size() >= MAX_PLAYERS) {
         throw new IllegalStateException("Maximum number of players reached");
      }
      if (!players.isEmpty()) {
         for (Player player : players) {
            if (player.getName().equals(playerName)) {
                throw new IllegalArgumentException("Player name already exists");
            }
         }
      }
      players.add(new Player(playerName));
      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public void roll(int roll) {
      Player currentPlayer = players.get(currentPlayerIndex);
      System.out.println(currentPlayer.getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (currentPlayer.isInPenaltyBox()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
            movePlayer(currentPlayer, roll);
            askQuestion();
         } else {
            System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }
      } else {
         movePlayer(currentPlayer, roll);
         askQuestion();
      }
   }

   private void movePlayer(Player player, int roll) {
      player.move(roll, BOARD_SIZE);
      System.out.println(player.getName() + "'s new location is " + (player.getPosition() + 1));
      System.out.println("The category is " + currentCategory());
   }

   private void askQuestion() {
      System.out.println(questionDeck.askQuestion(currentCategory()));
   }

   private String currentCategory() {
        return categories.get(players.get(currentPlayerIndex).getPosition() % categories.size());
   }

   public boolean handleCorrectAnswer() {
      Player currentPlayer = players.get(currentPlayerIndex);
      if (currentPlayer.isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
         nextPlayer();
         return true;
      }

      System.out.println("Answer was correct!!!!");
      currentPlayer.incrementPurse();
      System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getPurse() + " Gold Coins.");

      boolean winner = !currentPlayer.hasWon(WINNING_SCORE);
      nextPlayer();
      return winner;
   }

   public boolean wrongAnswer() {
      Player currentPlayer = players.get(currentPlayerIndex);
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer.getName() + " was sent to the penalty box");
      currentPlayer.sendToPenaltyBox();

      nextPlayer();
      return true;
   }

   private void nextPlayer() {
      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public int howManyPlayers() {
      return players.size();
   }
}