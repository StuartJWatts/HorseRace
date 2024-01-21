package Parts;

public class RoundBet {
    // Attributes
    private int value;
    private String colour;

    // Constuctors
    public RoundBet(String colour, int value) {
        setColour(colour);
        setValue(value);
    }

    // Setters and Getters
    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
    // Methods

}
