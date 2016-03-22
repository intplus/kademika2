package Lesson2;

import java.awt.Color;

public class Launcher {

	public static void main(String[] args) {
		
		Tank tank = new Tank(TankColor.GREEN, 2, 80);
		
		BT7 tank1 = new BT7(TankColor.ORANGE);
		T34 tank2 = new T34();
		Tiger tank3 = new Tiger(5);
		tank.setName("tank");
		tank1.setName("BT7");
		tank2.setName("T34");
		tank3.setName("Tiger");
		

		System.out.println(tank.getName() + ": " + tank.toString());
		System.out.println(tank1.getName() + ": " + tank1.toString());
		System.out.println(tank2.getName() + ": " + tank2.toString());
		System.out.println(tank3.getName() + ": " + tank3.toString());
		
		tank1.move("UP");
		tank2.move("DOWN");
		tank3.move("LEFT");
		tank.move("RIGHT");

	}
	

}
