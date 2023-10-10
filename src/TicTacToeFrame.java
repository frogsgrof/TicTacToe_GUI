import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TicTacToeFrame extends JFrame {

    static int WIDTH, HEIGHT;
    final static int ROW = 3, COL = 3;
    static ImageIcon winIcon,
            tieIcon;
    TicTacToeTile[][] board;

    public TicTacToeFrame() {
        super();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int screenWidth = toolkit.getScreenSize().width;
        int screenHeight = toolkit.getScreenSize().height;
        WIDTH = screenHeight * 3 / 4;
        HEIGHT = WIDTH + 100;

        setSize(new Dimension(WIDTH, HEIGHT));
        setLocation((screenWidth - WIDTH) / 2, screenHeight / 32);

        setTitle("Tic Tac Toe");
        try {
            setIconImage(ImageIO.read(new File(System.getProperty("user.dir") +
                    "//images//tictactoe.png").toURI().toURL()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getRootPane().setBorder(BorderFactory.createLineBorder(getContentPane().getBackground(), 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel gamePnl = new JPanel();
        gamePnl.setOpaque(false);
        gamePnl.setPreferredSize(new Dimension(WIDTH, WIDTH));
        GridLayout gridLayout = new GridLayout(ROW, COL);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        gamePnl.setLayout(gridLayout);

        board = new TicTacToeTile[ROW][COL];
        for (int row = 0; row < ROW; row++)
            for (int col = 0; col < COL; col++) {
                board[row][col] = new TicTacToeTile();
                gamePnl.add(board[row][col]);
            }

        Font btnFont;
        try {
            btnFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File(System.getProperty("user.dir") + "//fonts//button_font.ttf"))
                    .deriveFont(Font.PLAIN, 20f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        JButton quitBtn = new JButton("Quit");
        quitBtn.setFont(btnFont);
        quitBtn.setFocusPainted(false);
        quitBtn.setPreferredSize(new Dimension(100, 50));
        quitBtn.addActionListener(e -> showQuitDialog());

        getContentPane().setLayout(new BorderLayout(10, 10));
        add(gamePnl, BorderLayout.PAGE_START);
        add(quitBtn, BorderLayout.EAST);

        try {
            winIcon = new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")
                    + "//images//trophy.png")));
            tieIcon = new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")
                    + "//images//squiggle.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showQuitDialog() {
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void showBadInputDialog() {
        JOptionPane.showMessageDialog(null, "That square is taken.");
    }

    public void showGameOverDialog(int result) {

        ImageIcon icon;

        String message = switch (result) {
            case TicTacToe.X_WIN -> {
                icon = winIcon;
                yield "X won! Play again?";
            }
            case TicTacToe.O_WIN -> {
                icon = winIcon;
                yield "O won! Play again?";
            }
            case TicTacToe.TIE -> {
                icon = tieIcon;
                yield TicTacToe.getMoveCount() == 9 ? "It's a tie (full board). Play again?" :
                        "It's a tie (non-full board). Play again?";
            }
            default -> throw new IllegalStateException("Unexpected value: " + result);
        };
        
        if (JOptionPane.showConfirmDialog(null, message, "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon) == JOptionPane.OK_OPTION)
            TicTacToe.startGame();
        else
            System.exit(0);
    }

    public TicTacToeTile[][] getBoard() {
        return board;
    }
}
