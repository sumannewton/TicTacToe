
/**
 * Created by suman.bn on 07/07/16.
 */
public abstract class Board {

    static Character[][] gameBoard;
    final static Character INITIALIZE_CHAR = ' ';

    public Board() {
        gameBoard = new Character[3][3];
        initializeBoard();
    }

    private void initializeBoard() {
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                gameBoard[x][y] = INITIALIZE_CHAR;
            }
        }
    }

    public void resetBoard() {
        initializeBoard();
    }

    public void displayBoard() {
        System.out.println("     ================");
        System.out.println("     | y0 | y1 | y2 |");
        System.out.println("=====================");
        for (int x = 0; x < 3; x++) {
            System.out.print("| x" + x + " ");
            for (int y = 0; y < 3; y++) {
                System.out.print("| " + gameBoard[x][y] + "  ");
            }
            System.out.println("|\n=====================");
        }
    }

    public Boolean isBoardFull() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if(gameBoard[x][y].equals(INITIALIZE_CHAR)) {
                    return false;
                }
            }
        }
        return true;
    }
}
