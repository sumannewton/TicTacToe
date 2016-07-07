import lombok.Setter;

import java.util.Scanner;

/**
 * Created by suman.bn on 07/07/16.
 */
public class Game extends Board {

    @Setter
    String playerOne;
    @Setter
    String playerTwo;
    @Setter
    String currentPlayer;
    Scanner scanner = new Scanner(System.in);

    private static int X_COORDINATE = -1;
    private static int Y_COORDINATE = -1;

    public Game() {
        super();
        initializePlayers();
    }

    public void setMarkOnBoard(Character c) {
        gameBoard[X_COORDINATE][Y_COORDINATE] = c;
    }

    public Boolean isWin() {
        return isRowWin() || isColWin() || isDiagonalWin();
    }

    private Boolean isRowWin() {
        for (int x = 0; x < 3; x++) {
            if (!(gameBoard[x][0].equals(INITIALIZE_CHAR)) &&
                    (gameBoard[x][0].equals(gameBoard[x][1]) && gameBoard[x][0].equals(gameBoard[x][2]))) {
                return true;
            }
        }
        return false;
    }

    private Boolean isColWin() {
        for (int y = 0; y < 3; y++) {
            if (!(gameBoard[0][y].equals(INITIALIZE_CHAR)) &&
                    (gameBoard[0][y].equals(gameBoard[1][y]) && gameBoard[0][y].equals(gameBoard[2][y]))) {
                return true;
            }
        }
        return false;
    }

    private Boolean isDiagonalWin() {
        if (!(gameBoard[1][1].equals(INITIALIZE_CHAR)) &&
                ((gameBoard[0][0].equals(gameBoard[1][1]) && gameBoard[0][0].equals(gameBoard[2][2])) ||
                (gameBoard[0][2].equals(gameBoard[1][1]) && gameBoard[0][2].equals(gameBoard[2][0])))) {
            return true;
        }
        return false;
    }

    private void switchPlayer() {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
        System.out.println("-------------------------");
    }

    private void nextPlayerTurn() {
        System.out.print("Enter x co-ordinate:");
        X_COORDINATE = scanner.nextInt();
        System.out.print("Enter y co-ordinate:");
        Y_COORDINATE = scanner.nextInt();
        try {
            if (!gameBoard[X_COORDINATE][Y_COORDINATE].equals(INITIALIZE_CHAR)) {
                throw new RuntimeException("Already filled by " + gameBoard[X_COORDINATE][Y_COORDINATE]);
            }
        } catch (Exception e) {
            System.out.println("******* Invalid co-ordinates:" + e.getMessage() + " *******");
            System.out.println("===Re-Enter the co-ordinates===");
            nextPlayerTurn();
        }
    }

    private void initializePlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter First Player Name:");
        this.setPlayerOne(scanner.next());
        System.out.print("Enter Second Player Name:");
        this.setPlayerTwo(scanner.next());
        this.setCurrentPlayer(playerOne);
    }

    public void start() {

        while (!isBoardFull()) {
            System.out.println(currentPlayer + "'s turn");
            displayBoard();
            nextPlayerTurn();
            setMarkOnBoard(currentPlayer.charAt(0));
            if (isWin()) {
                System.out.println(currentPlayer.toUpperCase() + " WINS!! Congratulations!!!");
                displayBoard();
                break;
            }
            switchPlayer();
        }

        if(isBoardFull() && !isWin()) {
            System.out.println("GAME DRAW!!!");
            displayBoard();
        }

        Boolean flag = true;
        while (flag) {
            System.out.println("Do you want to play again?");
            System.out.println("1.Re-Match\n2.New Game\n3.Exit");
            switch (scanner.nextInt()) {
                case 1:
                    flag = !flag;
                    resetBoard();
                case 2:
                    flag = !flag;
                    resetBoard();
                    initializePlayers();
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invaid Selection: Select Again");
            }
        }
        start();
    }

}
