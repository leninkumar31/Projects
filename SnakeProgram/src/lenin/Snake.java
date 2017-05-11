package lenin;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Snake {
	LinkedList<Cell> snake;
	int startX, startY, size;
	int DirX, DirY;
	Cell head;

	boolean isMoving, reSize, isGameOver;

	public Snake() {
		startX = 100;
		startY = 100;
		DirX = 0;
		DirY = 0;
		isMoving = false;
		reSize = false;
		size = 20;
		head = new Cell(startX, startY);
		snake = new LinkedList<Cell>();
		snake.add(head);
		for (int i = 1; i < size; i++) {
			snake.addLast(new Cell(startX + i * 4, startY));
		}
	}

	public void setDir(int x, int y) {
		DirX = x;
		DirY = y;
	}

	public int getDirX() {
		return DirX;
	}

	public int getDirY() {
		return DirY;
	}

	public void setReSize(boolean b) {
		reSize = b;
	}

	public boolean getReSize() {
		return reSize;
	}

	public void move() {
		// System.out.println(isMoving);
		if (isMoving) {
			// System.out.println((head.getX() + 4 * DirX) + " " + (head.getY()
			// + 4 * DirY));
			snake.addFirst(new Cell(head.getX() + 4 * DirX, head.getY() + 4 * DirY));
			head = snake.getFirst();
			if (!reSize){
				snake.removeLast();
			}
			/*
			 * System.out.println("change"); for(Cell c:snake)
			 * System.out.println(c.getX()+" "+c.getY());
			 */
		}
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean b) {
		isMoving = b;
	}

	public void setIsGameOver(boolean b) {
		isGameOver = b;
	}

	public boolean getIsGameOver() {
		return isGameOver;
	}

	public void isCollided() {
		if (head.getX() < 0 || head.getX() > 396) {
			setMoving(false);
			setIsGameOver(true);
		}
		if (head.getY() < 0 || head.getY() > 396) {
			setMoving(false);
			setIsGameOver(true);
		}
		int temp = 0;
		for (Cell c : snake) {
			if (temp == 0) {
				temp++;
				continue;
			}
			if (head.getX() == c.getX() && head.getY() == c.getY()) {
				setMoving(false);
				setIsGameOver(true);
				break;
			}
			temp++;
		}
	}

	public Cell getHead() {
		return head;
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		// System.out.println("draw");
		for (Cell c : snake) {
			// System.out.println(c.getX()+" "+c.getY());
			g.fillRect(c.getX(), c.getY(), 4, 4);
		}
		if (isGameOver) {
			g.setColor(Color.red);
			g.drawString("GameOver", 150, 150);
		}

	}

}
