package Lesson2;

public class Tank {
	private int crew;
	private int maxSpeed;
	private String name;
	private TankColor color;
	
	public Tank(TankColor color, int crew, int maxSpeed) {
		this.color = color;
		this.crew = crew;
		this.maxSpeed = maxSpeed;	
		
	}
	public Tank() {
		this(TankColor.BLACK, 3, 60);
	}
	public Tank(int crew) {
		this.crew = crew;
	}
	
	public void move(String dir) {
		System.out.println(this.getName() + " move " + dir);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TankColor getColor() {
		return color;
	}

	public void setColor(TankColor color) {
		this.color = color;
	}

	public int getCrew() {
		return crew;
	}
	public void setCrew(int crew) {
		this.crew = crew;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	@Override
	public String toString() {
	    return ("Tank color = " + this.getColor() + "; Tank crew = " + 
	    		this.getCrew() + "; Tank maxSpeed = " + this.getMaxSpeed());
	}
	

}
