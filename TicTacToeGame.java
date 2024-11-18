import java.util.Scanner;

public class TicTacToeGame {

    // 3x3 game board
    private char[][] board;
    private char currentPlayer;
    
    // Constructor initializes the board and starts with 'X'
    public TicTacToeGame() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Initialize the board with empty spaces
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Display the board to the players
    public void displayBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n---------");
        }
    }

    // Check if there is a winner
    public boolean checkWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)
                return true;
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)
                return true;
        }

        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)
            return true;
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)
            return true;

        return false;
    }

    // Check if the board is full (no more moves possible)
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Switch to the other player
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Make a move at the specified position
    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            return false;  // Invalid move
        }
        board[row][col] = currentPlayer;
        return true;
    }

    // Start the game
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayBoard();
            System.out.println("Player " + currentPlayer + "'s turn.");
            System.out.print("Enter row and column (0-2) separated by space: ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // Make the move if valid
            if (!makeMove(row, col)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            // Check for winner
            if (checkWinner()) {
                displayBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            // Check for draw
            if (isBoardFull()) {
                displayBoard();
                System.out.println("The game is a draw!");
                break;
            }

            // Switch player
            switchPlayer();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
    }
}
