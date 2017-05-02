package assets;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.Game;

public class Ball 
{
	private static final int DIAMETER = 30;
	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
	public static int speed = 1;
	private Game game;

	public Ball(Game game) 
	{
		this.game = game;
	}

	public void move()
	{
		@SuppressWarnings("unused")
		boolean changeDirection = true;
		if (x + xa < 0)
			xa = speed;
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -speed;
		else if (y + ya < 0)
			ya = speed;
		else if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		else if (collision()){
			ya = -speed;
			y = game.getPaddle().getTopY() - DIAMETER;
			speed++;
		}
		else
		{
			changeDirection = false;
		}
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() 
	{
		return game.getPaddle().getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) 
	{
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() 
	{
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}