package Lesson4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ActionField extends JPanel {
	final boolean COLORDED_MODE = false;
	private BattleField bf;
	private Tank t;
	private Bullet b;
	
	void runTheGame() throws Exception {
		long time = System.currentTimeMillis() / 1000;
	
		clean();
		destroy();
		
		time = System.currentTimeMillis() / 1000 - time;
		int min = (int) time / 60;
		time = time % 60;
		int sec = (int) time;
		System.out.print(min + " min" + ":" + sec + " sec");

	}
	void destroy() throws Exception {
		t.setX(-100);
		t.setY(-100);
		repaint();
	}
	void clean() throws Exception {
		cleanPoint();
		while (cleanField()) {
			String quad = radar_v4();
			int quadY = giveY(quad);
			int quadX = giveX(quad);

			System.out.println("move to: " + bf.coordFieldX(quadX) + " : " + bf.coordFieldY(quadY));

			if (checkQuadrant(quadX, quadY))
				moveToQuadrant(bf.coordFieldX(quadX), bf.coordFieldY(quadY));
			cleanPoint();

		}
	}
//	bf.qwX()
//	bf.qwY()
	void moveToQuadrant(int v, int h) throws Exception {
		int x = 0;
		int y = 0;
		while (x != v) {
			x = bf.coordFieldX(t.getX()/bf.QUADRANT);
			if (x > v) {
				t.turn(t.LEFT);
				t.move();
			}	
			if (x < v) {
				t.turn(t.RIGHT);
				t.move();
			}	
		}
		while (y != h) {
			y = bf.coordFieldY(t.getY()/bf.QUADRANT);
			if (y > h) {
				t.turn(t.UP);
				t.move();
			}	
			if (y < h) {
				t.turn(t.DOWN);
				t.move();
			}	
		}
	}
	
	void cleanPoint() throws Exception {
		for (int i = 1; i < 5; i++) {
			t.direction = i;
			while (lineScanner(t.direction)) {
				t.fire();
			}
		}
	}
	boolean lineScanner(int direction) throws Exception {
		String quad = getQuadrant(t.getX(), t.getY());
		int quadY = giveY(quad);
		int quadX = giveX(quad);

		if (checkQuadrant(quadX, quadY)) {
			switch (direction) {
			case 1:
				for (int i = quadY; i >= 0; i--) {
					if (bricks(i, quadX))
						return true;
				}
				break;
			case 2:
				for (int i = quadY; i < bf.getDimentionY(); i++) {
					if (bricks(i, quadX))
						return true;
				}
				break;
			case 3:
				for (int i = quadX; i >= 0; i--) {
					if (bricks(quadY, i))
						return true;
				}
				break;
			case 4:
				for (int i = quadX; i < bf.getDimentionX(); i++) {
					if (bricks(quadY, i))
						return true;
				}
				break;
			}

		}
		return false;
	}
	String nextTankCoord(int direction) throws Exception {
		int nextTankX = 0;
		int nextTankY = 0;

		switch (direction) {
		case 1:
		case 2:
			nextTankY = direction == 1 ? t.getY() - bf.QUADRANT : t.getY() + bf.QUADRANT;
			nextTankX = t.getX();

			break;
		case 3:
		case 4:
			nextTankY = t.getY();
			nextTankX = direction == 3 ? t.getX() - bf.QUADRANT : t.getX() + bf.QUADRANT;

			break;
		}
		return getQuadrant(nextTankX, nextTankY);

	}
	void scanner(int direction) throws Exception {
		String quad = nextTankCoord(direction);
		int quadY = giveY(quad);
		int quadX = giveX(quad);

		if (checkBricks(quadY, quadX)) {
			t.fire();
		}

	}
	boolean checkQuadrant(int x, int y) throws Exception {
		if (x >= 0 && x < bf.getBfWidth() && y >= 0 && y < bf.getBfHeight())
			return true;
		return false;
	}
	boolean bricks(int v, int h) throws Exception {
		if (bf.scanQuadrant(v, h).equals("B"))
			return true;
		else
			return false;
	}

	boolean empty(int v, int h) throws Exception {
		if (bf.scanQuadrant(v, h).equals("") || bf.scanQuadrant(v, h).equals(" "))
			return true;
		else
			return false;
	}

	boolean checkBricks(int i, int k) throws Exception {
		if (checkQuadrant(i, k) && bricks(i, k))
			return true;
		return false;
	}
	boolean cleanField() throws Exception {
		int count = 0;
		for (int v = 0; v < bf.getDimentionY(); ++v) {
			for (int h = 0; h < bf.getDimentionX(); ++h) {
				if (empty(v, h))
					count++;
			}
		}
		if (win(count))
			return false;
		return true;
	}
	String radar_v4() throws Exception {
		int min = bf.getBfHeight() * 64;
		String min_coord = "0_0";
		for (int i = 0; i < bf.getDimentionY(); ++i) {
			for (int k = 0; k < bf.getDimentionX(); ++k) {
				if ((checkBricks(i, k)) && (dlina(getQuadrantXY(bf.coordFieldX(i), bf.coordFieldY(k))) < min)) {
					min = dlina(getQuadrantXY(bf.coordFieldX(i), bf.coordFieldY(k)));
					min_coord = i + "_" + k;
				}
			}
		}
		return min_coord;
	}
	boolean win(int count) throws Exception {
		if (count == 81) {
			System.out.println("WIN");
			return true;
		}
		return false;
	}
	int dlina(String str) throws Exception {
		int x = giveX(str) - t.getX();
		int y = giveY(str) - t.getY();
		int rez = (int) Math.sqrt(x * x + y * y);
		return rez;
	}

	
	int giveX(String quad) throws Exception {
		return Integer.parseInt(quad.substring(quad.indexOf("_") + 1));
	}

	int giveY(String quad) throws Exception {
		return Integer.parseInt(quad.substring(0, quad.indexOf("_")));
	}
	private boolean processInterception() throws Exception {
		String quad = getQuadrant(b.getX(), b.getY());
		int quadY = giveY(quad);
		int quadX = giveX(quad);
		if (quadX > 8 || quadY > 8) return false;
		if (bf.scanQuadrant(quadY, quadX).equals("B")) {
			bf.updateQuadrant(quadY, quadX, "");
			return true;
		}
		return false;
	}

	String getQuadrantXY(int v, int h) {
		return bf.QUADRANT * (v - 1) + "_" + bf.QUADRANT * (h - 1);
	}

	String getQuadrant(int x, int y) throws Exception {
		return (int) y / bf.QUADRANT + "_" + (int) x / bf.QUADRANT;
	}
	public void processTurn(Tank t)  throws Exception {
		repaint();
	}
	public void processMove(Tank t) throws Exception {
		this.t = t;
		int direction = t.getDirection();
		int step = 1;
		int covered = 0;

		if (noValidMove(direction)) {
			System.out.println("ignor");
			return;
		}

		t.turn(direction);
		scanner(t.direction);

		while (covered < bf.QUADRANT) {
			switch (direction) {
			case 1:
			case 2:
				t.updateY(direction == t.UP ? - step : step);
				break;
			case 3:
			case 4:
				t.updateX(direction == t.LEFT ? -step : step);
				break;
			}
			covered += step;
			newPicture(t.getSpeed());
		}

	}
	void newPicture(int time) throws Exception {
		repaint();
		Thread.sleep(time);
	}
	boolean noValidMove(int direction) throws Exception {
		if ((direction == t.UP && t.getY() == 0) || (direction == t.DOWN && t.getY() >= bf.QUADRANT * (bf.getDimentionX() - 1))
				|| (direction == t.LEFT && t.getX() == 0)
				|| (direction == t.RIGHT && t.getX() >= bf.QUADRANT * (bf.getDimentionY() - 1)))
			return true;
		else
			return false;
	}
	void processFire(Bullet b) throws Exception {
		this.b = b;
		int step = 1;
		while (checkBullet()) {

			if (processInterception()) {
				b.distroy();
			} else 
				switch (b.getDirection()) {
				case 1:
				case 2:
					b.updateY(b.getDirection() == 1 ? -step : step);
					break;
				case 3:
				case 4:
					b.updateX(b.getDirection() == 3 ? -step : step);
					break;		
			}
			newPicture(b.getSpeed());

		}

	}
	boolean checkBullet() throws Exception {
		if ((b.getX() > -14 && b.getX() <= bf.SIZE_FIELD * bf.QUADRANT + 10)
				&& (b.getY() > -14 && b.getY() <= bf.SIZE_FIELD * bf.QUADRANT + 10))
			return true;
		return false;
	}
	/*
	 * magic
	 */

	public ActionField() throws Exception {
		bf = new BattleField();
		t = new Tank(this, bf);
		b = new Bullet(-100, -100, -1);
		
		JFrame frame = new JFrame("BATTLE FIELD, DAY 4");
		frame.setLocation(750, 150);
		frame.setMinimumSize(new Dimension(bf.getBfWidth(), bf.getBfHeight() + 22));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int i = 0;
		Color cc;
		for (int v = 0; v < bf.getDimentionY(); v++) {
			for (int h = 0; h < bf.getDimentionX(); h++) {
				if (COLORDED_MODE) {
					if (i % 2 == 0) {
						cc = new Color(204, 255, 204);
					} else {
						cc = new Color(233, 243, 255);
					}
				} else {
					cc = new Color(138, 246, 138);
				}
				i++;
				g.setColor(cc);
				g.fillRect(h * bf.QUADRANT, v * bf.QUADRANT, bf.QUADRANT, bf.QUADRANT);
			}
		}

		for (int j = 0; j < bf.getDimentionY(); j++) {
			for (int k = 0; k < bf.getDimentionX(); k++) {
				if (bf.scanQuadrant(j, k).equals("B")) {
					String coordinates = getQuadrantXY(j + 1, k + 1);
					int separator = coordinates.indexOf("_");
					int y = Integer.parseInt(coordinates.substring(0, separator));
					int x = Integer.parseInt(coordinates.substring(separator + 1));
					g.setColor(new Color(239, 126, 13));
					g.fillRect(x, y, bf.QUADRANT, bf.QUADRANT);

					g.setColor(new Color(149, 133, 117));
					for (int m = 0; m < bf.QUADRANT; m += (bf.QUADRANT / 4))
						g.drawLine(x, y + m, x + 63, y + m);

					for (int m = 0, count = 0; m < bf.QUADRANT; m += 16, count += 1) {
						int dl = 0;
						if (count % 2 == 0)
							dl = 10;
						for (int l = 0; l < bf.QUADRANT; l += 24) {
							g.drawLine(x + l + dl, y + m, x + l + dl, (y + 16) + m);
						}
					}
				}
			}
		}

		g.setColor(new Color(255, 255, 0));
		g.fillRect(b.getX(), b.getY(), 14, 14);

		g.setColor(new Color(0, 0, 0));
		g.fillRect(t.getX() + 20, t.getY() + 20, 24, 24);

		g.setColor(new Color(30, 58, 238));

		if (t.getDirection() == 1) {
			g.fillRect(t.getX() + 12, t.getY() + 16, 8, 32);
			g.fillRect(t.getX() + 44, t.getY() + 16, 8, 32);
			g.fillRect(t.getX() + 28, t.getY() + 4, 8, 34);
		}
		if (t.getDirection() == 2) {
			g.fillRect(t.getX() + 12, t.getY() + 16, 8, 32);
			g.fillRect(t.getX() + 44, t.getY() + 16, 8, 32);
			g.fillRect(t.getX() + 28, t.getY() + 26, 8, 34);
		}
		if (t.getDirection() == 3) {
			g.fillRect(t.getX() + 16, t.getY() + 12, 32, 8);
			g.fillRect(t.getX() + 16, t.getY() + 44, 32, 8);
			g.fillRect(t.getX() + 4, t.getY() + 28, 34, 8);
		}
		if (t.getDirection() == 4) {
			g.fillRect(t.getX() + 16, t.getY() + 12, 32, 8);
			g.fillRect(t.getX() + 16, t.getY() + 44, 32, 8);
			g.fillRect(t.getX() + 26, t.getY() + 28, 34, 8);
		}
	}
}
