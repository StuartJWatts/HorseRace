public class RaceBet {
    // Attributes
    private String colour;
    private String player;

    // Constuctors
    public RaceBet(String colour, String player) {
        setColour(colour);
        setPlayer(player);
    }

    // Setters and Getters
    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }
    // Methods

}
