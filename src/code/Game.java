package code;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener {

	// Variables:
	static boolean play = false;
	static boolean loss = false;
	static int score = 0;
	static int totalBricks;
	static int level = 1;

	// Timer Variable:
	Timer timer;
	int delay = 8;

	// Player and ball Variables:
	int paddle = 310;
	// Starts the ball from the paddle
	int ballpositionX = 350;
	int ballpositionY = 530;
	int balldirectionX = -3;
	int balldirectionY = -4;

	// Bricks as object
	Bricks bricks;

	public Game() {

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		reset();

	}

	public void reset() {

		if (level == 1) {
			score = 0;
			paddle = 310;
			totalBricks = 21;
			ballpositionX = 350;
			ballpositionY = 530;
			balldirectionX = -2;
			balldirectionY = -6;
			bricks = new Bricks(3, 7);

		}
		if (level == 2) {
			score = 0;
			paddle = 310;
			totalBricks = 28;
			ballpositionX = 350;
			ballpositionY = 530;
			balldirectionX = -4;
			balldirectionY = -5;
			bricks = new Bricks(4, 7);
		}
		if (level == 3) {
			score = 0;
			paddle = 310;
			totalBricks = 35;
			ballpositionX = 350;
			ballpositionY = 530;
			balldirectionX = -5;
			balldirectionY = -6;
			bricks = new Bricks(5, 7);
		}
		if(level == 4){
			repaint();
		}


	}

	public void endGame() {
		JOptionPane.showMessageDialog(null, "Your Total Score is " + score+ ". Click 'OK' to Restart level and to Exit click 'OK' then 'X'.","Game Over!",JOptionPane.OK_CANCEL_OPTION);
       	repaint();

	}

	public void paint(Graphics g) {
		if(loss){
			play = false;
			loss = false;					
			reset();
		}

		if (!loss) {
			// Window background:
			g.setColor(Color.black);
			g.fillRect(1, 1, 692, 592);

			// Paddle
			g.setColor(Color.white);
			g.fillRect(paddle, 550, 100, 8);
			
			if(level == 1){
				// Ball
				g.setColor(Color.RED);
				g.fillOval(ballpositionX, ballpositionY, 15, 15);
			}
			if(level == 2){
				// Ball
				g.setColor(Color.MAGENTA);
				g.fillOval(ballpositionX, ballpositionY, 15, 15);
			}
			if(level == 3){
				// Ball
				g.setColor(Color.cyan);
				g.fillOval(ballpositionX, ballpositionY, 15, 15);
			}

			// Bricks
			bricks.draw((Graphics2D) g);

			// Score
			g.setColor(Color.white);
			g.setFont(new Font("Sans", Font.BOLD, 25));
			g.drawString("Score: " + score, 550, 40);
			
			// Level
			g.setColor(Color.white);
			g.setFont(new Font("Sans", Font.BOLD, 25));
			g.drawString("Level: " + level, 50, 40);

			g.dispose();
		}


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// Start the timer
		timer.start();

		// ball movement
		if (play == true) {

			// Detect Paddle each time it is created/moved
			if (new Rectangle(ballpositionX, ballpositionY, 20, 20).intersects(new Rectangle(paddle, 550, 100, 8))) {
				// make ball rebound and go up after hitting the paddle
				balldirectionY = -balldirectionY;
			}

			// move the ball
			ballpositionX = ballpositionX + balldirectionX;
			ballpositionY = ballpositionY + balldirectionY;


			// detect and break bricks
			for (int x = 0; x < bricks.bricks.length; x++) {
				for (int y = 0; y < bricks.bricks[0].length; y++) {
					if (bricks.bricks[x][y] > 0) {
						int brickX = y * bricks.brickWidth + 50;
						int brickY = x * bricks.brickHeight + 50;
						int brickWidth = bricks.brickWidth;
						int brickHeight = bricks.brickHeight;

						Rectangle rectangle = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballpositionX, ballpositionY, 20, 20);
						Rectangle brickRect = rectangle;

						if (ballRect.intersects(brickRect)) {
							bricks.BricksValues(0, x, y);
							totalBricks--;
							score = score + 10;

							if (ballpositionX + 20 <= brickRect.x || ballpositionX + 1 <= brickRect.width) {
								balldirectionY = -balldirectionY;
							} else {
								balldirectionY = -balldirectionY;
							}
						}
						if (totalBricks == 0) {
							play = false;
							level++;
							reset();
						}
					}
				}
			}

			// Ball hits left
			if (ballpositionX < 10) {
				balldirectionX = -balldirectionX;
			}

			// Ball hits right
			if (ballpositionX > 670) {
				balldirectionX = -balldirectionX;
			}

			// Ball hits top
			if (ballpositionY < 10) {
				balldirectionY = -balldirectionY;
			}
			// Ball missed
			if (ballpositionY > 550) {
				loss = true;
				play = false;
				endGame();
			}

		}

		// Repaint the paddle and bar as they are moved.
		repaint();

		if (!loss && totalBricks == 0) {
			// create new level
			level++;
			play = false;
			timer.stop();
			reset();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// When right key is pressed
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			if (paddle >= 600) {
				paddle = 580;
			} else {
				moveRight();
			}
		}

		// When left key is pressed
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			if (paddle < 10) {
				paddle = 10;
			} else {
				moveLeft();
			}
		}

	}

	public void moveRight() {
		play = true;
		paddle = paddle + 45;
	}

	public void moveLeft() {
		play = true;
		paddle = paddle - 45;
	}

}