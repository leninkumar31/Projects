package lenin;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends Applet implements Runnable, KeyListener {

	Graphics gfx;
	Image img;
	Snake s;
	Apple apple;
	Thread thread;

	public void init() {
		this.resize(400, 400);
		img = createImage(400, 400);
		gfx = img.getGraphics();
		this.addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
		s = new Snake();
		apple = new Apple();
		thread = new Thread(this);
		thread.start();
	}

	public void paint(Graphics g) {
		gfx.setColor(Color.BLACK);
		gfx.fillRect(0, 0, 400, 400);
		s.draw(gfx);
		gfx.setColor(Color.RED);
		gfx.fillRect(apple.getX(), apple.getY(), 4, 4);
		if (s.getIsGameOver()) {
			gfx.setColor(Color.RED);
			gfx.drawString("Score :" + Integer.toString(apple.getScore()), 160, 170);
		}
		g.drawImage(img, 0, 0, null);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (!s.isMoving()) {
			if (e.getKeyCode() != KeyEvent.VK_RIGHT)
				s.setMoving(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (s.getDirY() != 1)
				s.setDir(0, -1);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (s.getDirY() != -1)
				s.setDir(0, 1);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (s.getDirX() != 1)
				s.setDir(-1, 0);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (s.getDirX() != -1)
				s.setDir(1, 0);
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void run() {
		while (true) {
			if (!s.getIsGameOver()) {
				s.move();
				s.isCollided();
				isEatable();
			}
			repaint();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void isEatable() {
		int sx = s.getHead().getX();
		int sy = s.getHead().getY();
		int ax = apple.getX();
		int ay = apple.getY();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int tx = sx + i;
				int ty = sy + j;
				for (int k = 0; k < 4; k++) {
					for (int l = 0; l < 4; l++) {
						if (tx == ax + k && ty == ay + l) {
							s.setReSize(true);
							apple.generateApple();
							return;
						}
					}
				}
			}
		}
		s.setReSize(false);
		/*
		 * if ((s.getHead().getX() >= apple.getX() - 1 && s.getHead().getY() >=
		 * apple.getY() - 1) && (s.getHead().getX() <= apple.getX() + 5 &&
		 * s.getHead().getY() <= apple.getY() + 5)) { s.setReSize(true);
		 * apple.generateApple(); } else s.setReSize(false);
		 */
	}

}
