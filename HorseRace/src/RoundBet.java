public class RoundBet extends RaceBet {
    // Attributes
    private int value;

    // Constuctors
    public RoundBet(String colour, int value) {
        super(colour);
        setValue(value);
    }

    // Setters and Getters
    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    // Methods

}
