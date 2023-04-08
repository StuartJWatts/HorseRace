import java.util.ArrayList;
import java.util.Stack;

public class Game {
    public static void main(String[] args) {
        System.out.print("welcome to the game");
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
