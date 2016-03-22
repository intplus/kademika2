package Lesson2;

public class T34 extends Tank {
	
	private int maxSpeed = 50;
	public T34() {
		
	}
	
	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	@Override
	public String toString() {
		return "Tank color = " + this.getColor() + "; Tank crew = " + 
							this.getCrew() + "; Tank maxSpeed = " + this.getMaxSpeed();
	}
	@Override
	public void move(String dir) {
		System.out.println(this.getName() + " move " + dir);
		
	}
	

	

}
