package tank_object;

public class Tank {

	private int x;
	private int y;
	public int direction;
	private ActionField af;
	private BattleField bf;
	public int STEP = 1;
	public int SPEED = 10;
	
	final public static int UP = 1;
	final public static int DOWN = 2;
	final public static int LEFT = 3;
	final public static int RIGHT = 4;
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public Tank(ActionField af, BattleField bf) {
		this(af, bf, 128, 512, 1);
	}
	public Tank(ActionField af, BattleField bf, int x, int y, int direction) {
		this.af = af;
		this.bf = bf;
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	void turn(int direction) throws Exception {
		this.direction = direction;
		af.processTurn(this);
	}

	void move() throws Exception {
		af.processMove(this);
	}

	void fire() throws Exception {
		Bullet b = new Bullet((x + 25), (y + 25), direction);
		af.processFire(b);
	}
	
	public int getSpeed() {
		return SPEED;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDirection() {
		return direction;
	}

	public void moveRandom() throws Exception {
		
	}
	public void moveToQuadrant(int v, int h) throws Exception {
		
	}
	public void clean() throws Exception {
		
	}
	public void updateX (int x) {
		this.x += x;
	}
	public void updateY (int y) {
		this.y += y;
	}

}
