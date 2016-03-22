package Lesson4;

public class Bullet {
	final static int UP = 1;
	final static int DOWN = 2;
	final static int LEFT = 3;
	final static int RIGHT = 4;
	
	int speed = 2;
	int x;
	int y;
	int direction;
	
	public Bullet(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setDirection(int dir) {
		 this.direction = dir;
	}
	public int getDirection() {
		return direction;
	}
	public int getSpeed() {
		return speed;
	}
	public void updateX (int dx) {
		this.x += dx;
	}
	public void updateY (int dy) {
		this.y += dy;
	}
	public void distroy() {
		x = -100;
		y = -100;
	}


}
