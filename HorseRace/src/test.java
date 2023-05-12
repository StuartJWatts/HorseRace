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
        String[] winnerAndLoser = getFirstAndLast(board);
        System.out.printf("\n\n%s wins!!!\n\n", winnerAndLoser[0]);
        System.out.printf("\n\n%s losses!!!\n\n", winnerAndLoser[1]);

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
            Stack<RaceBet> losserBets, ArrayList<Stack<Horse>> board) {
        // go through the stack of bets, award money to the players
        String[] firstAndLast = getFirstAndLast(board);
        Stack<RaceBet> winFlip = new Stack<>();
        Stack<RaceBet> loseFlip = new Stack<>();
        RaceBet temp;
        while (winnerBets.size() > 0) {
            winFlip.push(winnerBets.pop());
        }
        while (losserBets.size() > 0) {
            loseFlip.push(losserBets.pop());
        }

        int winCount = 0;
        int loseCount = 0;

        while (winFlip.size() > 0) {
            temp = winFlip.pop();
            Player tempPlayer = temp.getPlayer();
            if (temp.getColour().equals(firstAndLast[0])) {

                switch (winCount) {
                    case 0: {
                        tempPlayer.addMoney(8);
                        winCount++;
                        break;
                    }
                    case 1: {
                        tempPlayer.addMoney(5);
                        winCount++;
                        break;
                    }
                    case 2: {
                        tempPlayer.addMoney(3);
                        winCount++;
                        break;
                    }
                    case 3: {
                        tempPlayer.addMoney(2);
                        winCount++;
                        break;
                    }
                    case 4: {
                        tempPlayer.addMoney(1);
                        winCount++;
                        break;
                    }
                    case 5: {
                        tempPlayer.addMoney(0);
                        winCount++;
                        break;
                    }
                    default: {
                        tempPlayer.addMoney(0);
                        winCount++;
                        break;
                    }
                }
            } else {
                tempPlayer.addMoney(-1);
            }

        }

        while (loseFlip.size() > 0) {
            temp = loseFlip.pop();
            Player tempPlayer = temp.getPlayer();
            if (temp.getColour().equals(firstAndLast[1])) {

                switch (loseCount) {
                    case 0: {
                        tempPlayer.addMoney(8);
                        loseCount++;
                        break;
                    }
                    case 1: {
                        tempPlayer.addMoney(5);
                        loseCount++;
                        break;
                    }
                    case 2: {
                        tempPlayer.addMoney(3);
                        loseCount++;
                        break;
                    }
                    case 3: {
                        tempPlayer.addMoney(2);
                        loseCount++;
                        break;
                    }
                    case 4: {
                        tempPlayer.addMoney(1);
                        loseCount++;
                        break;
                    }
                    case 5: {
                        tempPlayer.addMoney(0);
                        loseCount++;
                        break;
                    }
                    default: {
                        tempPlayer.addMoney(0);
                        break;
                    }
                }
            } else {
                tempPlayer.addMoney(-1);
            }

        }

    }

    public static String[] getFirstAndLast(ArrayList<Stack<Horse>> board) {
        // return the winner and the last place horse
        // return string with winner in index 0 and loser in index 1

        // use get winners to get first place
        // find first no zero stack in race direction
        // if horse in not black or white then add it to the string finish
        String[] firstAndLast = new String[2];
        firstAndLast[0] = Game.getWinners(board)[0];

        boolean found = false;
        for (int i = 0; i < board.size(); i++) {
            Stack<Horse> temp = (Stack<Horse>) board.get(i).clone();
            Stack<Horse> tempFlip = new Stack<>();
            if (temp.size() >= 1) {
                System.out.print("1");
                // flip stack
                // pop and check each horse if black, white or other
                while (temp.size() > 0) {
                    tempFlip.push(temp.pop());
                    System.out.print("2");
                }
                while (tempFlip.size() > 0) {
                    Horse tempHorse = tempFlip.pop();
                    System.out.print("3");
                    System.out.print(tempHorse.getColour());
                    if (tempHorse.getColour() != "black" && tempHorse.getColour() != "white") {
                        firstAndLast[1] = tempHorse.getColour();
                        found = true;
                        break;
                    }
                }

            } else {
                continue;
            }

            if (found) {
                System.out.print("break");
                break;
            }

        }
        return firstAndLast;
    }

    public static void displayRoundBets(ArrayList<Stack<RoundBet>> roundBets) {
        // display the round bets remaining on the board
    }

}
