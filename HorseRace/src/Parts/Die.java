package Parts;

public class Die {
    // Attributes
    private String colour;

    // Constuctors
    public Die(String colour) {
        setColour(colour);
    }

    // Setters and Getters
    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    // Methods
    public int roll() {
        return (int) (Math.random() * 3) + 1;
    }
}
