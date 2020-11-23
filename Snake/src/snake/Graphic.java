package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class Graphic extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		//BG
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 700);
		
		//Snake
		Snake snake = Snake.snake;
		g.setColor(Color.white);
		for (Point point : snake.snakebody) {
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE,  Snake.SCALE,  Snake.SCALE);
		}
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE,  Snake.SCALE,  Snake.SCALE);
		
		g.setColor(Color.red);
		g.fillRect(snake.food.x * Snake.SCALE, snake.food.y * Snake.SCALE, Snake.SCALE,  Snake.SCALE);
		
		String sScore = "Score: " + snake.score + " || length of the snake: " + snake.sLength;
		g.setColor(Color.pink);
		g.drawString(sScore, (int) (getWidth() - sScore.length() * 6), 10);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));
		
		if (snake.gameOver) {
			g.drawString("Snake Died", 100, snake.height / 2 - 50);
			g.setFont(new Font("Arial", 1, 30));
			g.drawString("press space to restart ", 110, snake.height / 2);
		}
		
	}

}
