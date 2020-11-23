package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {

	public static Snake snake;
	public JFrame jFrame;
	public Graphic graphic;
	public final int width = 800, height = 700; 
	public Timer timer;
	public Dimension dim;
	public ArrayList<Point> snakebody = new ArrayList<Point>();
	public Random random;
	public Point head, food;
	public boolean gameOver, pause;
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	public int sLength, ticks, direction, score;
	
	
	public Snake() {
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame = new JFrame();
		jFrame.setSize(width, height);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
		jFrame.setLocation(dim.width / 2 - jFrame.getWidth() / 2, dim.height / 2 - jFrame.getHeight() / 2);
		jFrame.add(graphic = new Graphic());
		jFrame.setTitle("~~ Snake ~~");
		jFrame.addKeyListener(this);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer = new Timer(20, this);
		start();
	}
	
	public void start() {
		snakebody.clear();
		pause = false;
		ticks = 0;
		score = 0;
		sLength = 2;
		direction = DOWN;
		gameOver = false;
		head = new Point(0, 0);
		random = new Random();
		food = new Point(random.nextInt(80), random.nextInt(67));
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		graphic.repaint();
		
		ticks ++;
		
		if (ticks % 5 == 0 && head != null && gameOver != true && !pause) {
			snakebody.add(new Point(head.x, head.y));
			if (direction == UP) {
				if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
					head = new Point(head.x, head.y - 1);
				} else {
					gameOver = true;
				}
			}
			if (direction == DOWN) {
				if (head.y + 1 < 67 && noTailAt(head.x, head.y + 1)) {
					head = new Point(head.x, head.y + 1);
				} else {
					gameOver = true;
				}
			}
			if (direction == LEFT) {
				if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
					head = new Point(head.x - 1, head.y);
				} else {
					gameOver = true;
				}
			}
			if (direction == RIGHT) {
				if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y)) {
					head = new Point(head.x + 1, head.y);
				} else {
					gameOver = true;
				}
			}
			
			if (snakebody.size() > sLength) {
				snakebody.remove(0);
			}

			if (food != null) {
				System.out.println("you: " + head.x + "||" + head.y + " food: " + food.x + "||" + food.y);
				if (head.equals(food)) {
					System.out.println("Rua");
					score ++;
					sLength ++;
					food.setLocation(random.nextInt(80), random.nextInt(67));
				}
			}
		}
		
	}
	
	private boolean noTailAt(int x, int y) {
		
		for (Point point : snakebody) {
			if (point.equals(new Point(x, y))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if (i == KeyEvent.VK_A && direction != RIGHT) {
			direction = LEFT;
		}
		if (i == KeyEvent.VK_D && direction != LEFT) {
			direction = RIGHT;
		}
		if (i == KeyEvent.VK_W && direction != DOWN) {
			direction = UP;
		}
		if (i == KeyEvent.VK_S && direction != UP) {
			direction = DOWN;
		}
		if (i == KeyEvent.VK_SPACE && gameOver) {
			start();
		}
		if (i == KeyEvent.VK_PAUSE) {
			if (pause == true) {
				pause = false;
			} else {
				pause = true;
			}
		}
	}
	
	public static void main(String[] args) {
		snake = new Snake();
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
