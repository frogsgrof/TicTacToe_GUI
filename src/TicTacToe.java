/**
 * @author Wulft
 */
public class TicTacToe {

    private static final int ROW = 3,
            COL = 3;
    static final int MOVES_FOR_WIN = 5,
            MOVES_FOR_TIE = 7;
    static final int X_WIN = 1,
            O_WIN = 2,
            TIE = 0;

    static TicTacToeTile[][] board;
    private static Player turn;
    static int moveCount;
    static TicTacToeFrame frame;

    public static void startGame() {
        turn = Player.X;
        moveCount = 0;

        for (int row = 0; row < ROW; row++)
            for (int col = 0; col < COL; col++)
                board[row][col].clear();
    }

    public static void makeMove() {
        moveCount++;

        if (moveCount >= MOVES_FOR_WIN)
            if (isWin())
                frame.showGameOverDialog(turn == Player.X ? X_WIN : O_WIN);

        if (moveCount >= MOVES_FOR_TIE)
            if (isTie())
                frame.showGameOverDialog(TIE);

        turn = turn == Player.X ? Player.O : Player.X;
    }

    private static boolean isWin() {
        return isColWin() || isRowWin() || isDiagonalWin();
    }

    private static boolean isColWin() {
        for (int col = 0; col < COL; col++)
            if (board[0][col].isOwnedBy(turn) &&
                    board[1][col].isOwnedBy(turn) &&
                    board[2][col].isOwnedBy(turn))
                return true;

        return false; // no col win
    }

    private static boolean isRowWin() {
        for (int row = 0; row < ROW; row++)
            if (board[row][0].isOwnedBy(turn) &&
                    board[row][1].isOwnedBy(turn) &&
                    board[row][2].isOwnedBy(turn))
                return true;

        return false; // no row win
    }

    private static boolean isDiagonalWin() {
        return (board[0][0].isOwnedBy(turn) &&
                board[1][1].isOwnedBy(turn) &&
                board[2][2].isOwnedBy(turn)) || (board[0][2].isOwnedBy(turn) &&
                board[1][1].isOwnedBy(turn) &&
                board[2][0].isOwnedBy(turn));
    }

    private static boolean isTie() {
        boolean xFlag = false;
        boolean oFlag = false;

        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].isOwnedBy(Player.X) ||
                    board[row][1].isOwnedBy(Player.X) ||
                    board[row][2].isOwnedBy(Player.X))
                xFlag = true; // there is an X in this row

            if (board[row][0].isOwnedBy(Player.O) ||
                    board[row][1].isOwnedBy(Player.O) ||
                    board[row][2].isOwnedBy(Player.O))
                oFlag = true; // there is an O in this row

            if (!(xFlag && oFlag))
                return false; // No tie can still have potential a row win

            xFlag = oFlag = false;
        }

        // Now scan the columns
        for (int col = 0; col < COL; col++) {
            if (board[0][col].isOwnedBy(Player.X) ||
                    board[1][col].isOwnedBy(Player.X) ||
                    board[2][col].isOwnedBy(Player.X))
                xFlag = true; // there is an X in this col

            if (board[0][col].isOwnedBy(Player.O) ||
                    board[1][col].isOwnedBy(Player.O) ||
                    board[2][col].isOwnedBy(Player.O))
                oFlag = true; // there is an O in this col

            if (!(xFlag && oFlag))
                return false; // No tie can still have a potential col win

        }

        // Now check for the diagonals
        xFlag = oFlag = false;

        if (board[0][0].isOwnedBy(Player.X) ||
                board[1][1].isOwnedBy(Player.X) ||
                board[2][2].isOwnedBy(Player.X))
            xFlag = true;

        if (board[0][0].isOwnedBy(Player.O) ||
                board[1][1].isOwnedBy(Player.O) ||
                board[2][2].isOwnedBy(Player.O))
            oFlag = true;

        if (!(xFlag && oFlag))
            return false; // No tie can still have a potential diag win

        xFlag = oFlag = false;

        if (board[0][2].isOwnedBy(Player.X) ||
                board[1][1].isOwnedBy(Player.X) ||
                board[2][0].isOwnedBy(Player.X))
            xFlag = true;

        if (board[0][2].isOwnedBy(Player.O) ||
                board[1][1].isOwnedBy(Player.O) ||
                board[2][0].isOwnedBy(Player.O))
            oFlag = true;

        return xFlag && oFlag; // No tie can still have a diag win
    }

    public static Player getTurn() {
        return turn;
    }
    public static int getMoveCount() {
        return moveCount;
    }

    public static void setFrame(TicTacToeFrame frame) {
        TicTacToe.frame = frame;
    }

    public static void setBoard(TicTacToeTile[][] board) {
        TicTacToe.board = board;
    }
}
