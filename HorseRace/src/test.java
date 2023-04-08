import java.util.ArrayList;
import java.util.Stack;

public class test {
    public static void main(String[] args) {
        ArrayList<Stack<Horse>> board = new ArrayList<>();
        String[] colours = new String[] { "red", "blue", "green", "yellow", "purple" };
        Stack<Die> dieStack = Game.makeStack();

        for (int i = 0; i < 16; i++) {
            board.add(new Stack<Horse>());
        }
        for (String col : colours) {
            board.get(0).add(new Horse(col));
        }
        Game.displayBoard(board);
        moveHorse(board, dieStack);
        Game.displayBoard(board);

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
        System.out.print(colour);
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
        if (newIndex > 15) {
            return true;
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
        return false;

    }

}
