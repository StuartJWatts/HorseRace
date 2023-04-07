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
        raceBets.add(new RaceBet("Red"));
        raceBets.add(new RaceBet("Green"));
        raceBets.add(new RaceBet("Blue"));
        raceBets.add(new RaceBet("Yellow"));
        raceBets.add(new RaceBet("Purple"));
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

    // Methods

}
