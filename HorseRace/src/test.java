import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Stack<Horse>> board = new ArrayList<>();
        ArrayList<Player> players = Game.getPlayers(in);
        Stack<RaceBet> winRaceBets = new Stack<>();
        Stack<RaceBet> loseRaceBets = new Stack<>();
        int roundStartIndex = (int) Math.random() * (players.size() + 1);
        int currentPlayerIndex;

        boolean gameOver = false;

        for (int i = 0; i < 16; i++) {
            board.add(new Stack<Horse>());
        }

        Game.initialSetup(board);
        Game.displayBoard(board);

        while (!gameOver) {// overall game loop
            currentPlayerIndex = roundStartIndex;
            Stack<Die> dieStack = Game.makeStack();
            ArrayList<Stack<RoundBet>> roundBets = Game.setRoundBets();

            while (!dieStack.isEmpty()) {// round loop
                Player currentPlayer = players.get(currentPlayerIndex);
                if (currentPlayerIndex < players.size() - 1) {
                    currentPlayerIndex++;
                } else {
                    currentPlayerIndex = 0;
                }

                int userChoice = Game.getTurnChoice(currentPlayer.getName(), in);
                switch (userChoice) {
                    case -1: {
                        System.out.print("error incorrect input");
                        break;
                    }
                    case 1: {
                        gameOver = Game.moveHorse(board, dieStack);
                        Game.displayBoard(board);
                        currentPlayer.addMoney(1);
                        break;
                    }
                    case 2: {
                        // add defensive programming here
                        RoundBet bet = null;
                        while (bet == null) {
                            System.out.println("which colour would you like");
                            String betChoice = in.next();
                            bet = Game.getRoundBet(betChoice, roundBets);
                            if (bet == null) {
                                System.out.print("that stack is empty ");
                            }

                        }
                        currentPlayer.addRoundBet(bet);
                        break;
                    }
                    case 3: {
                        System.out.println("what colour");
                        String betcolour = in.next();
                        System.out.print(placeRaceBet(currentPlayer, betcolour, winRaceBets));
                        Iterator<RaceBet> iter = currentPlayer.getRaceBets().iterator();
                        while (iter.hasNext()) {
                            System.out.print(iter.next().getColour() + " ");
                        }
                        break;
                    }

                }

                if (gameOver)
                    break;

                if (roundStartIndex < players.size() - 1) {
                    roundStartIndex++;
                } else {
                    roundStartIndex = 0;
                }

            }
            System.out.println("round over\n");
            // get first and second place at end of round
            String[] winners = Game.getWinners(board);
            System.out.println("First place: " + winners[0]);
            System.out.println("Second place: " + winners[1]);

            // loop through players and add up round bets
            Iterator<Player> x = players.iterator();
            while (x.hasNext()) {
                x.next().calculateRoundBets(winners[0], winners[1]);
            }

            Iterator<Player> p = players.iterator();
            while (p.hasNext()) {
                Player temp = p.next();
                System.out.print(temp.getName() + " : " + temp.getMoney() + "\n");
            }
        }

        System.out.printf("\n\n%s wins!!!\n\n", board.get(15).pop().getColour());

    }

    public static boolean placeRaceBet(Player player, String colour, Stack<RaceBet> bet) {
        // check if user still has the race bet they want to bet,
        // if they dont return false.
        // if they do remove it from thier stack and add it to the specific stack\
        ArrayList<RaceBet> raceBets = player.getRaceBets();
        int size = player.getRaceBets().size();
        if (size == 0) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                if (raceBets.get(i).getColour().equals(colour)) {
                    bet.add(raceBets.remove(i));
                    return true;
                }

            }
            return false;
        }
    }

    public static void calculateRaceBets(ArrayList<Player> players, Stack<RaceBet> winnerBets,
            Stack<RaceBet> losserBets) {
        // go through the stack of bets, award money to the players
    }

    public static void displayRoundBets(ArrayList<Stack<RoundBet>> roundBets) {
        // display the round bets remaining on the board
    }

}
