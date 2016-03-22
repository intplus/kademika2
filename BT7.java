package Lesson2;

public class BT7  extends Tank {
	private int maxSpeed = 72;
	private TankColor color;
	
	public BT7(TankColor color) {
		this.color = color;
		
	}
	
	public BT7() {
		
	}
	
	@Override
	public void move(String dir) {
		System.out.println(this.getName() + " move " + dir);
		
	}
	
	@Override
	public String toString() {
		return "Tank color = " + this.getColor() + "; Tank crew = " + 
							this.getCrew() + "; Tank maxSpeed = " + this.getMaxSpeed();
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

}
