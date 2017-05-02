package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import assets.Ball;
import assets.Paddle;

@SuppressWarnings( "serial" )
public class Game extends JPanel 
{
	private Ball ball = new Ball( this );
	private Paddle paddle = new Paddle( this );
	
	@SuppressWarnings("static-access")
	private int getScore()
	{
		return ball.speed - 1;
	}

	public Game() 
	{
		addKeyListener( new KeyListener() 
		{
			@Override
			public void keyTyped( KeyEvent e ) 
			{
				//not sure if I want to use this or not
			}

			@Override
			public void keyReleased( KeyEvent e ) 
			{
				getPaddle().keyReleased( e );
			}

			@Override
			public void keyPressed( KeyEvent e ) 
			{
				getPaddle().keyPressed( e );
			}
		});
		setFocusable( true );
	}
	
	private void move() 
	{
		ball.move();
		getPaddle().move();
	}

	@Override
	public void paint( Graphics g ) 
	{
		super.paint( g );
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		
		ball.paint( g2d );
		
		getPaddle().paint( g2d );
		
		g2d.setColor( Color.BLACK );
		g2d.setFont( new Font( "Verdana", Font.BOLD, 30 ) );
		g2d.drawString( String.valueOf( getScore() ), 10, 30 );
	}
	
	public void gameOver() 
	{
		JOptionPane.showMessageDialog( this, "Game Over...", "", JOptionPane.YES_NO_OPTION );
		System.exit( ABORT );
	}

	public static void main(String[] args) throws InterruptedException 
	{
		JFrame frame = new JFrame( "Mini Tennis" );
		Game game = new Game();
		
		frame.add( game );
		frame.setSize( 300, 400 );
		frame.setVisible( true );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		while( true ) 
		{
			game.move();
			game.repaint();
			Thread.sleep( 10 );
		}
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
}