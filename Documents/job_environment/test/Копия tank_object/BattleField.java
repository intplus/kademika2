package tank_object;

import java.util.Arrays;
import java.util.Random;

public class BattleField {
	final int SIZE_FIELD = 9; // allowed to change
	final int QUADRANT = 64;
	final int BF_WIDTH = QUADRANT * SIZE_FIELD;
	final int BF_HEIGHT = QUADRANT * SIZE_FIELD;
	private Tank t;
	
	private String[][] battleField = new String[SIZE_FIELD][SIZE_FIELD];
	
//	public BattleField() {
//		
//	}
	
	public BattleField() throws Exception{
		firstCleanField();
		firstBricksConstruction();
//		firstTankWithOutBricks();

	}

	
	private int ran(int x) throws Exception {
		Random r = new Random();
		int i = r.nextInt(x);
		if (i == 0)
			i = x;
		return i;
	}
	private void firstBricksConstruction() throws Exception {
		int frequencyBrick = 2; // the more, the less shot
		for (int v = 0; v < battleField.length; ++v) {
			for (int h = 0; h < battleField[v].length; ++h) {
				if (ran(frequencyBrick) == 1)
					battleField[v][h] = "B";
			}
		}
	}

	void firstCleanField() throws Exception {
		for (int v = 0; v < battleField.length; ++v)
			Arrays.fill(battleField[v], " ");

	}
	public String scanQuadrant(int v, int h) {
		return battleField[v][h];
		
	}
	public void updateQuadrant(int v, int h, String object) {
		battleField[v][h] = object;
	}
	public int getDimentionX() {
		return battleField.length;
	}
	public int getDimentionY() {
		return battleField.length;
	}
	public int getBfWidth() {
		return BF_WIDTH;
	}
	public int getBfHeight() {
		return BF_HEIGHT;
	}
	
	
}
