import java.util.ArrayList;

public class Player {
    // Attributes
    private String name;
    private int money;
    private ArrayList<RaceBet> raceBets;
    private ArrayList<RoundBet> roundBets;

    // Constuctors
    public Player() {
        this("Anon");
    }

    public Player(String name) {
        setName(name);
        setMoney(3);
        roundBets = new ArrayList<>();
        raceBets = new ArrayList<>();
        raceBets.add(new RaceBet("red"));
        raceBets.add(new RaceBet("green"));
        raceBets.add(new RaceBet("blue"));
        raceBets.add(new RaceBet("yellow"));
        raceBets.add(new RaceBet("purple"));
    }

    // Setters and Getters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void addRoundBet(RoundBet bet) {
        roundBets.add(bet);
    }

    public ArrayList<RoundBet> getRoundBets() {
        return roundBets;
    }

    public RaceBet placeRaceBet(String colour) {
        // remove racebet of a certain colour from hand and return it
        // deal with capitals here?
        // make all lower case?
        // when user enters in the command line, hide the entry
        // https://stackoverflow.com/questions/10819469/hide-input-on-command-line
        return null;
    }

    // Methods

}
