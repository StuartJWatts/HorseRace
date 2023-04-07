public class Horse {
    // Attributes
    private String colour;
    private int currentPosition;

    // Constuctors
    public Horse() {
        this("White", 0);
    }

    public Horse(String colour, int currentPosition) {
        setColour(colour);
        setCurrentPosition(currentPosition);
    }
    // Setters and Getters

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public void setCurrentPosition(int currentPosition) {
        if (currentPosition > 0 && currentPosition < 16) {
            this.currentPosition = currentPosition;
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
    // Methods

}
