package workshop;

import java.util.ArrayList;
import java.util.List;

public class Questions implements Game{

    private List<Player> players = new ArrayList<>();
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    TriviaGame obj = new TriviaGame();

    @Override
    public boolean wasCorrectlyAnswered() {
        if (players.get(currentPlayer).getInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                obj.announce("Answer was correct!!!!");

                players.get(currentPlayer).advancedPurses(currentPlayer);

                boolean winner = obj.didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {

            obj.announce("Answer was correct!!!!");
            players.get(currentPlayer).advancedPurses(currentPlayer);
            boolean winner = obj.didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }

    }

    @Override
    public boolean wasWrongAnswered() {
        obj.announce("Question was incorrectly answered");
        obj.announce(players.get(currentPlayer) + " was sent to the penalty box");
        players.get(currentPlayer).setPenaltyBox(true);

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;

    }
}
