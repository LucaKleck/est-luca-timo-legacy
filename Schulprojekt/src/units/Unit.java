package units;

public abstract class Unit {
	private static int unitAmount = 0;
	private int unitID;
	private int range;
	private int healthPoints;
	public Unit(int range, int healthPoints) {
		this.unitID = unitAmount;
		unitAmount++;
		this.range = range;
		this.healthPoints = healthPoints;
	}
	public int getUnitID() {
		return unitID;
	}
	public int getUnitAmount() {
		return unitAmount;
	}
	public int getRange() {
		return range;
	}
	public int getHealthPoints() {
		return healthPoints;
	}
}
