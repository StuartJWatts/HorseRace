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
                        RoundBet bet = null;
                        while (bet == null) {
                            System.out.println("which colour would you like");
                            String betChoice = in.next();
                            bet = Game.getRoundBet(betChoice, roundBets);
                            if (bet == null) {
                                System.out.print("that stack is empty");
                            }

                        }
                        currentPlayer.addRoundBet(bet);
                        break;
                    }
                    case 3: {
                        System.out.println("Place race bet");
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
            for (String winner : winners) {
                System.out.println(winner);
            }

            // loop through players and add up round bets
            Iterator<Player> x = players.iterator();
            while (x.hasNext()) {
                x.next().calculateRoundBets(null, null);
            }
        }

        System.out.printf("\n\n%s wins!!!\n\n", board.get(15).pop().getColour());

    }

}
