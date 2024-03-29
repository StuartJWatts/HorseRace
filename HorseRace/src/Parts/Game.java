package Parts;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

/*
 * ToDo
 * -find cause for missing horses -done
 * -add round bets -done
 * -add display current round bets
 * -add race bets - done
 * -add defensive prog and logic for placing race bets
 * -add money tracking -done 
 * -add jump forward and reverse card
 * -add reverse horses
 * -make user redo turn choice if roundbet stack is empty or does not have race bet left
 * -hide racebet colour entry field
 * -defensive programming on user input
 * -fix calc round winners
 * 
 * 
 */

public class Game {
    public static void main(String[] args) {
        System.out.print("welcome to the game");
    }

    public static void initialSetup(ArrayList<Stack<Horse>> board) {
        Stack<Die> tempDie = Game.makeStack();
        Die die;
        String colour;
        int roll;
        while (!tempDie.isEmpty()) {
            die = tempDie.pop();
            colour = die.getColour();
            roll = die.roll();
            board.get(roll - 1).add(new Horse(colour));

        }
    }

    public static Stack<Die> makeStack() {
        Stack<Die> dieStack = new Stack<>();
        String[] colours = new String[] { "red", "blue", "green", "yellow", "purple" };

        while (dieStack.size() < 5) {
            int index = (int) (Math.random() * 5);
            boolean flag = true;
            if (dieStack.size() == 0) {
                dieStack.add(new Die(colours[index]));

            } else {
                for (int i = 0; i < dieStack.size(); i++) {

                    if (dieStack.get(i).getColour() == colours[index]) {
                        flag = false;
                    }
                }
                if (flag) {

                    dieStack.add(new Die(colours[index]));
                }
            }

        }
        return dieStack;
    }

    public static boolean moveHorse(ArrayList<Stack<Horse>> board, Stack<Die> dieStack) {
        // get roll info, find matching horse, move horse and horses on top by the
        // number of steps
        Die rolled = dieStack.pop();
        String colour = rolled.getColour();
        int steps = rolled.roll();
        // get index of stack containing that horse
        int index = 0;// index of stack that contains the horse that is rolled
        int i = 0;
        boolean gameOver = false;
        System.out.printf("%s : %d\n", colour, steps);
        for (Stack<Horse> stack : board) {
            for (Horse horse : stack) {
                if (horse.getColour() == colour) {
                    index = i;
                    break;
                }

            }
            i++;
        }
        int newIndex = index + steps;
        if (newIndex >= 15) {
            newIndex = 15;
            gameOver = true;

        }
        Stack<Horse> tempS = new Stack<>();
        while (!board.get(index).isEmpty()) {
            Horse tempH = board.get(index).pop();
            tempS.push(tempH);
            if (tempH.getColour() == colour) {
                break;
            }
        }

        while (!tempS.isEmpty()) {
            board.get(newIndex).push(tempS.pop());

        }
        return gameOver;

    }

    public static void displayBoard(ArrayList<Stack<Horse>> board) {
        System.out.println("\n------|START|-----");
        for (Stack<Horse> stack : board) {
            System.out.print("| ");
            Stack<Horse> temp1 = (Stack<Horse>) stack.clone();
            Stack<Horse> temp2 = new Stack<>();
            while (!temp1.isEmpty()) {
                Horse TEMP = temp1.pop();
                temp2.add(TEMP);

            }
            while (!temp2.isEmpty()) {
                System.out.print(temp2.pop().toString() + " ");

            }
            System.out.print("\n");

        }
        System.out.print("------|FINISH|-----\n");

    }

    public static ArrayList<Player> getPlayers(Scanner in) {
        ArrayList<Player> players = new ArrayList<>();
        int numberOfPlayers = 0;
        boolean finishedNum = false;
        String name;

        while (!finishedNum) {
            try {
                System.out.print("Enter the number of players: ");
                numberOfPlayers = in.nextInt();
                finishedNum = true;
            } catch (InputMismatchException e) {
                System.out.print("Error, enter an integer");
                in.next();
                System.out.print("\n");
            }
        }

        for (int i = 1; i <= numberOfPlayers; i++) {
            try {
                System.out.printf("Enter the %s's players name: ", i);
                name = in.next();
                players.add(new Player(name));
            } catch (InputMismatchException e) {
                System.out.print(e);
                i--;
            }
        }

        return players;
    }

    public static int getTurnChoice(String name, Scanner in) {
        int userChoice = -1;
        boolean finished = false;

        while (!finished) {
            try {
                System.out.println(name + " it is your turn: ");
                System.out.println("(1)Roll the dice");
                System.out.println("(2)Take round bet");
                System.out.println("(3)Place race bet");
                userChoice = in.nextInt();
                finished = true;
            } catch (InputMismatchException e) {
                System.out.print("Error, enter an integer");
                in.next();
                System.out.print("\n");
            }
        }

        return userChoice;

    }

    public static ArrayList<Stack<RoundBet>> setRoundBets() {
        ArrayList<Stack<RoundBet>> roundBets = new ArrayList<>();
        String[] colours = new String[] { "red", "blue", "green", "yellow", "purple" };
        int index = 0;
        for (int i = 0; i < 5; i++) {
            roundBets.add(new Stack<RoundBet>());
        }
        for (Stack<RoundBet> colourStack : roundBets) {
            colourStack.add(new RoundBet(colours[index], 2));
            colourStack.add(new RoundBet(colours[index], 2));
            colourStack.add(new RoundBet(colours[index], 3));
            colourStack.add(new RoundBet(colours[index], 5));
            index++;
        }

        return roundBets;
    }

    public static RoundBet getRoundBet(String colour, ArrayList<Stack<RoundBet>> roundBets) {
        // get index that contains that colour
        int index = 0;// index of stack that contains the horse that is rolled
        int i = 0;
        for (Stack<RoundBet> stack : roundBets) {
            if (stack.size() == 0) {
                break;
            }
            if (stack.peek().getColour().equals(colour)) {
                index = i;
                break;
            }
            i++;
        }
        // get bet on the top of that stack
        if (roundBets.get(index).size() > 0) {
            return roundBets.get(index).pop();
        } else {
            return null;
        }
    }

    public static String[] getWinners(ArrayList<Stack<Horse>> board) {
        // find last stack that is non zero
        // check if last non zero has more than one
        // if more than one, take top 2
        // if only one get the first then get the next stack that is non zero
        // get second place horse
        String[] winners = new String[2];
        int stringIndex = 0;
        for (int i = board.size() - 1; i > 0; i--) {
            Stack<Horse> temp = (Stack<Horse>) board.get(i).clone();
            if (temp.size() > 1) {// get the colour of the two top horses
                while (!temp.isEmpty() && stringIndex < 2) {

                    winners[stringIndex] = temp.pop().getColour();
                    stringIndex++;
                    break;
                }
            } else if ((temp.size() == 1) && stringIndex < 2) {

                winners[stringIndex] = temp.pop().getColour();
                stringIndex++;
            } else {
                continue;
            }

        }

        return winners;

    }
}