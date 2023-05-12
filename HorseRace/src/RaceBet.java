public class RaceBet {
    // Attributes
    private String colour;
    private Player player;

    // Constuctors
    public RaceBet(String colour, Player player) {
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    // Methods

}
