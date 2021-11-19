package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wave {
    public int speed;
    public int x;
    public int y;
    Timer timeMove;

    public Wave(int x, int y, int speed, JPanel page) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.move(page);
    }

    public void move(final JPanel page) {
        this.timeMove = new Timer(this.speed, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Wave.this.x <= 0) {
                    Wave.this.x = (int)(1000.0D + 300.0D + Math.random() * 1000.0D);
                }

                Wave var10000 = Wave.this;
                var10000.x -= 30;
                page.repaint();
            }
        });
        this.timeMove.start();
    }

    public BufferedImage getImage() {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File("img\\tree.png"));
            return image;
        } catch (Exception var3) {
            var3.printStackTrace();
            return image;
        }
    }
}

