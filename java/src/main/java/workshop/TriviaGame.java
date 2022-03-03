package workshop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TriviaGame {

    private List<Player> players = new ArrayList<>();

    List<String> popQuestions = new LinkedList();
    List<String> scienceQuestions = new LinkedList();
    List<String> sportsQuestions = new LinkedList();
    List<String> rockQuestions = new LinkedList();

    int currentPlayer;
    boolean isGettingOutOfPenaltyBox;

    public TriviaGame() {
        for (int i = 0; i < 50; i++) {
            popQuestions.add("Pop Question " + i);
            scienceQuestions.add(("Science Question " + i));
            sportsQuestions.add(("Sports Question " + i));
            rockQuestions.add(createRockQuestion(i));
        }
    }

    //method isPlayable deleted

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean add(String playerName) {

        //Player class has been made to ensure inline class generation
        players.add(new Player((playerName)));
        players.get(currentPlayer).place();
        players.get(currentPlayer).purses();
        players.get(currentPlayer).getInPenaltyBox();

        announce(playerName + " was added");
        announce("They are player number " + players.size());
        return true;
    }

    //howManyPlayers() is dead code

    public void roll(int roll) {
        announce(players.get(currentPlayer) + " is the current player");
        announce("They have rolled a " + roll);

        if (players.get(currentPlayer).getInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                announce(players.get(currentPlayer) + " is getting out of the penalty box");
                advancePlayer(roll);
                //duplicated code is reduced by extract method

                announce(players.get(currentPlayer)
                        + "'s new location is "
                        + players.get(currentPlayer).place());
                announce("The category is " + currentCategory());
                askQuestion();
            } else {
                announce(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            advancePlayer(roll);

            //duplicated code is reduced by extracting method

            announce(players.get(currentPlayer)
                    + "'s new location is "
                    + players.get(currentPlayer).place());
            announce("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void advancePlayer(int roll)
    {
        roll(roll);
        players.get(currentPlayer).movePlayer(roll);
        //Using move method to move a method into the Player class
    }


    private void askQuestion() {
        if ("Pop".equals(currentCategory()))
            announce(popQuestions.remove(0));
        if ("Science".equals(currentCategory()))
            announce(scienceQuestions.remove(0));
        if ("Sports".equals(currentCategory()))
            announce(sportsQuestions.remove(0));
        if ("Rock".equals(currentCategory()))
            announce(rockQuestions.remove(0));
    }


    private String currentCategory() {
        if (players.get(currentPlayer).place() == 0) return "Pop";
        if (players.get(currentPlayer).place() == 4) return "Pop";
        if (players.get(currentPlayer).place() == 8) return "Pop";
        if (players.get(currentPlayer).place() == 1) return "Science";
        if (players.get(currentPlayer).place() == 5) return "Science";
        if (players.get(currentPlayer).place() == 9) return "Science";
        if (players.get(currentPlayer).place() == 2) return "Sports";
        if (players.get(currentPlayer).place() == 6) return "Sports";
        if (players.get(currentPlayer).place() == 10) return "Sports";
        return "Rock";
    }


    public boolean didPlayerWin() {
        return !(players.get(currentPlayer).purses() == 6);
    }

    protected void announce(Object message) {
        System.out.println(message);
    }

}