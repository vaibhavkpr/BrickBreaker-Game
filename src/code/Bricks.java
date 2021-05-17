package code;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bricks {

	// Variables for the bricks
	static int brickWidth = 0;
	static int brickHeight = 0;
	static int level = 1;
	static int[][] bricks;

	public Bricks(int rows, int columns) {

		bricks = new int[rows][columns];
		
		if(rows == 3){
			level = 1;
		}
		if(rows == 4){
			level = 2;
		}
		if(rows == 5){
			level = 3;
		}
		
		//Level 1
		if (level == 1) {
			
			//Set Level 1 bricks rows and columns
			columns = 6;
			rows = 4;

			for (int x = 0; x < bricks.length; x++) {
				for (int y = 0; y < bricks[0].length; y++) {
					// sets all bricks as value of 1
					bricks[x][y] = 1;
				}
			}
			// Set brick dimensions
			brickWidth = 510/ columns;
			brickHeight = 100 / rows;

		}

		if (level == 2) {
			
			
			columns = 6;
			rows = 4;

			for (int x = 0; x < bricks.length; x++) {
				for (int y = 0; y < bricks[0].length; y++) {
					// sets all bricks as value of 1
					bricks[x][y] = 1;
				}
			}
			// Set brick dimensions
			brickWidth = 510/ columns;
			brickHeight = 100 / rows;

		}

		if (level == 3) {

			columns = 6;
			rows = 4;

			for (int x = 0; x < bricks.length; x++) {
				for (int y = 0; y < bricks[0].length; y++) {
					// sets all bricks as value of 1
					bricks[x][y] = 1;
				}
			}
			// Set brick dimensions
			brickWidth = 510/ columns;
			brickHeight = 100 / rows;

		}

	}

	public void draw(Graphics2D g) {
		// Create bricks
		for (int x = 0; x < bricks.length; x++) {
			for (int y = 0; y < bricks[0].length; y++) {
				// check if bricks exist and draws them
				if (bricks[x][y] > 0) {
					if(level == 1){
						g.setColor(Color.orange);
					}
					if(level == 2){
						g.setColor(Color.green);
					}
					if(level == 3){
						g.setColor(Color.red);
					}
					
					g.fillRect(y * brickWidth + 50, x * brickHeight + 50, brickWidth, brickHeight);

					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(y * brickWidth + 50, x * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void BricksValues(int value, int row, int column){
		
		bricks[row][column] = value;
	}

}