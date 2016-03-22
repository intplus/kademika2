package Lesson2;

public class Tiger extends Tank {
	int maxSpeed = 36;
	int armor = 1;
	int crew;
	
	public Tiger() {
		
	}
	public Tiger(int crew) {
		this.crew = crew;
		
	}
	
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
	public void printTankInfo() {
        System.out.println("Tank color = " + this.getColor() + "; Tank crew = " + this.getCrew() +
                "; Tank maxSpeed = " + this.getMaxSpeed() + "; Tank armor = " + this.armor);
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

}
