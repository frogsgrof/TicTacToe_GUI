import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @author wulft
 */
public class TicTacToeTile extends JButton {
    static ImageIcon X_ICON, O_ICON;
    Player player;

    public TicTacToeTile() {
        super();
        addActionListener(e -> setSelected());
        setOpaque(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        super.paintComponent(g);
    }

    public static void getImages() {
        String directory = System.getProperty("user.dir") + "//images//";
        X_ICON = new ImageIcon(directory + "//x.png");
        O_ICON = new ImageIcon(directory + "//o.png");
    }

    private void setSelected() {
        if (player == null) {
            setIcon(TicTacToe.getTurn() == Player.X ? X_ICON : O_ICON);
            player = TicTacToe.getTurn();
            TicTacToe.makeMove();
        } else {
            TicTacToeFrame.showBadInputDialog();
        }
    }

    public void clear() {
        player = null;
        setIcon(null);
    }

    public boolean isOwnedBy(Player player) {
        return this.player != null && Objects.equals(this.player, player);
    }
}
