package lenin;

public class Apple {
	int x, y;
	int score;

	public Apple() {
		x = (int) (Math.random() * 394);
		y = (int) (Math.random() * 394);
		score = 0;
	}

	public void generateApple() {
		x = (int) (Math.random() * 394);
		y = (int) (Math.random() * 394);
		score++;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
    
	public int getScore()
	{
		return score;
	}
}
