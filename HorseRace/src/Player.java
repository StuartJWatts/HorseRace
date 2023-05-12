import java.util.ArrayList;
import java.util.Iterator;

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
        raceBets.add(new RaceBet("red", this));
        raceBets.add(new RaceBet("green", this));
        raceBets.add(new RaceBet("blue", this));
        raceBets.add(new RaceBet("yellow", this));
        raceBets.add(new RaceBet("purple", this));
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

    public ArrayList<RaceBet> getRaceBets() {
        return raceBets;
    }

    public RaceBet placeRaceBet(int index) {
        // remove racebet of a certain colour from hand and return it
        // deal with capitals here?
        // make all lower case?
        // when user enters in the command line, hide the entry
        // https://stackoverflow.com/questions/10819469/hide-input-on-command-line
        return raceBets.remove(index);
    }

    // Methods

    public void addMoney(int money) {
        int currentMoney = this.getMoney();
        this.setMoney(currentMoney + money);
    }

    public void calculateRoundBets(String firstPlace, String secondPlace) {
        Iterator<RoundBet> x = this.getRoundBets().iterator();
        while (x.hasNext()) {
            RoundBet bet = (RoundBet) x.next();
            if (bet.getColour() == firstPlace) {
                this.addMoney(bet.getValue());
            } else if (bet.getColour() == secondPlace) {
                this.addMoney(1);
            } else {
                this.addMoney(-1);
            }
        }
        this.getRoundBets().clear();

    }

}
