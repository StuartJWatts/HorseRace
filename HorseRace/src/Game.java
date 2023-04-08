import java.util.ArrayList;
import java.util.Stack;

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
        System.out.printf("%s : %d", colour, steps);
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
}
