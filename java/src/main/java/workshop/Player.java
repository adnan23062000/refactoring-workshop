package workshop;

public class Player {

    private final String name;
    private int place;
    private int purses;
    private boolean inPenaltyBox;


    public Player(String name) {
        this.name = name;
    }

    public String name()
    {
        return this.name;
    }

    public int place()
    {
        return this.place;
    }

    public int purses()
    {
        return this.purses;
    }

    public boolean getInPenaltyBox()
    {
        return this.inPenaltyBox;
    }

    public void setPenaltyBox(boolean inPenaltyBox)
    {
        this.inPenaltyBox = inPenaltyBox;
    }

    public void setPurses(int purses)
    {
        this.purses = purses;
    }

    public void setPlace(int place)
    {
        this.place = place;
    }

    public void movePlayer(int roll)
    {
        setPlace(place()+roll);

        if(place() > 11)
            setPlace(place()-12);
    }

    public void advancedPurses(int currentPlayer)
    {
        setPurses(purses() + 1);
        System.out.println(
                " now has "
                + purses()
                + " Gold Coins.");
    }

}
