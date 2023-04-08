import java.util.ArrayList;
import java.util.Stack;

public class test {
    public static void main(String[] args) {
        ArrayList<Stack<Horse>> board = new ArrayList<>();
        boolean gameOver = false;

        for (int i = 0; i < 16; i++) {
            board.add(new Stack<Horse>());
        }

        Game.initialSetup(board);
        Game.displayBoard(board);

        while (!gameOver) {
            Stack<Die> dieStack = Game.makeStack();
            while (!dieStack.isEmpty()) {
                gameOver = Game.moveHorse(board, dieStack);
                Game.displayBoard(board);
                if (gameOver)
                    break;
            }
        }

        System.out.printf("\n%s Wins!", board.get(15).pop().getColour());

    }

}
