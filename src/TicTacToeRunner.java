public class TicTacToeRunner {

    public static void main(String[] args) {

        TicTacToeFrame ticTacToeFrame = new TicTacToeFrame();

        TicTacToeTile.getImages();

        ticTacToeFrame.setVisible(true);

        TicTacToe.setFrame(ticTacToeFrame);
        TicTacToe.setBoard(ticTacToeFrame.getBoard());
        TicTacToe.startGame();
    }
}