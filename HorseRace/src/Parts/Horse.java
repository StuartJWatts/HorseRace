package Parts;

public class Horse {
    // Attributes
    private String colour;

    // Constuctors
    public Horse() {
        this("White");
    }

    public Horse(String colour) {
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

    public String toString() {
        return (colour);
    }

}
