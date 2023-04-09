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
                        break;
                    }
                    case 2: {
                        System.out.println("which colour would you like");
                        String betChoice = in.next();
                        System.out.println(betChoice);
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
        }

        System.out.printf("\n\n%s wins!!!\n\n", board.get(15).pop().getColour());

    }

}
