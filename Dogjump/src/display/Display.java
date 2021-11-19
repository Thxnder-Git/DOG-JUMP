package display;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Display extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Dimension size = new Dimension(1000, 600);

    public Display() {
        this.setting();
        this.getContentPane().add(new Game());
    }

    private void setting() {
        this.setTitle("Dog ninja");
        this.setSize(this.size);
        this.setDefaultCloseOperation(3);
        this.setLocation(280, 100);
        this.setVisible(true);
    }

    private void removeContent() {
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
    }

    public void endGame(long point) {
        this.removeContent();
        this.getContentPane().add(new Menu(point, this));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("restart")) {
            this.removeContent();
            Game game = new Game();
            this.getContentPane().add(game);
            game.requestFocus();
        }

    }
}
