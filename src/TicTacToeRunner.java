public class TicTacToeRunner {

    /*
    Create a java Swing GUI application in a new IntelliJ project called TicTacToeGUI.
    Your project will have a TicTacToeFrame.java class and a java main class: TicTacToeRunner.java.
    Put your project under GitHub source control.

    The game is the same in every respect to the previous lab from CP I (where we refactored Tic Tac Toe console code
    from the text) except that there is now a GUI to handle the display of the game state and the input from the user.

    Starting with X each player alternates making a move by clicking on a square.

    Do not use any console (System.out.print…) output as this is a GUI program.

    TODO Provide many screen shots that establish that your game works correctly:
        Win dialog
        Tie dialogs  (Full board tie and the not full board tie)
        Looping until user enters a valid move
        Prompt to play another game.
     */

    static final int ROW = 3, COL = 3;

    public static void main(String[] args) {

        TicTacToeFrame ticTacToeFrame = new TicTacToeFrame();

        TicTacToeTile.getImages();

        ticTacToeFrame.setVisible(true);

        TicTacToe.setFrame(ticTacToeFrame);
        TicTacToe.setBoard(ticTacToeFrame.getBoard());
        TicTacToe.startGame();
    }
}