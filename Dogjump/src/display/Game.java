package display;

import Charactor.Dog;
import Charactor.Environment;
import Charactor.Wave;
import Element.Element;
import event.Event;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;
    private static int speed = 50;
    private static int dogSize = 60;
    private static int waveHeight = 50;
    private static int base = 400;
    private static int xStart = 1000;
    private long point = 0L;
    private long lastPress = 0L;
    private Dog dog;
    static Display display;
    private Wave[] waveSet;
    private Environment[] envSet;
    private Environment building;

    public Game() {
        this.dog = new Dog(100, base - 50);
        this.waveSet = this.makeWave(4);
        this.envSet = this.makeEnv(2, Environment.CLOUD);
        this.building = new Environment(xStart - 100, base - 150, this, Environment.BUILDING, 4);
        this.setBounds(0, 0, 1000, 600);
        this.addKeyListener(this);
        this.setLayout((LayoutManager)null);
        this.setFocusable(true);
    }

    public void paint(Graphics g) {
        try {
            super.paint(g);
            Graphics2D g2 = (Graphics2D)g;
            this.drawBackground(g2);
            g2.setFont(Element.getFont(30));
            g2.setColor(Color.white);
            g2.drawString("Point : " + this.point, 750, 40);
            g2.setColor(Color.RED);
            this.drawDogHealth(g2);
            g2.drawImage(this.dog.getImage(), this.dog.x, this.dog.y, dogSize, dogSize, (ImageObserver)null);
            Wave[] var6;
            int var5 = (var6 = this.waveSet).length;

            for(int var4 = 0; var4 < var5; ++var4) {
                Wave item = var6[var4];
                this.drawWave(item, g2);
            }

            ++this.point;
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    private void drawBackground(Graphics2D g2) throws IOException {
        g2.drawImage(ImageIO.read(new File("img\\sky.png")), 0, 0, 2000, 1000, (ImageObserver)null);
        g2.drawImage(this.building.getImage(), this.building.x, this.building.y, 500, 200, (ImageObserver)null);
        g2.drawImage(ImageIO.read(new File("img\\dir.png")), 0, base + 10, 2000, 220, (ImageObserver)null);
        Environment[] var5;
        int var4 = (var5 = this.envSet).length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Environment item = var5[var3];
            g2.drawImage(item.getImage(), item.x, item.y, 250, 160, (ImageObserver)null);
        }

    }

    private void drawDogHealth(Graphics2D g2) {
        try {
            g2.drawImage(ImageIO.read(new File("img\\heart.png")), 10, 20, 20, 20, (ImageObserver)null);
            g2.setStroke(new BasicStroke(18.0F));
            g2.setColor(new Color(241, 98, 69));
            g2.drawLine(60, 30, 60 + this.dog.health, 30);
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(6.0F));
            g2.drawRect(50, 20, 200, 20);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    private Wave[] makeWave(int size) {
        Wave[] waveSet = new Wave[size];
        int far = 500;

        for(int i = 0; i < size; ++i) {
            waveSet[i] = new Wave(xStart + far, base, speed, this);
            far += 500;
        }

        return waveSet;
    }

    private Environment[] makeEnv(int size, int eType) {
        Environment[] envSet = new Environment[size];
        int far = 0;

        for(int i = 0; i < size; ++i) {
            envSet[i] = new Environment(xStart + far, 20, this, eType, 10);
            far += 600;
        }

        return envSet;
    }

    private void drawWave(Wave wave, Graphics2D g2) {
        g2.drawImage(wave.getImage(), wave.x, wave.y - waveHeight, 40, waveHeight + 10, (ImageObserver)null);
        if (Event.checkHit(this.dog, wave, dogSize, waveHeight)) {
            g2.setColor(new Color(241, 98, 69));
            g2.fillRect(0, 0, 1000, 1000);
            Dog var10000 = this.dog;
            var10000.health -= 20;
            if (this.dog.health <= 0) {
                display.endGame(this.point);
                this.dog.health = (new Dog()).health;
                this.point = 0L;
            }
        }

    }

    public void keyPressed(KeyEvent e) {
        if (System.currentTimeMillis() - this.lastPress > 600L && (e.getKeyCode() == 32 || e.getKeyCode() == 38)) {
            this.dog.jump(this);
            this.lastPress = System.currentTimeMillis();
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] arg) {
        display = new Display();
    }
}