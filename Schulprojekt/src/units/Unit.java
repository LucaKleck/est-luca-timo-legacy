package units;

public abstract class Unit {
	private static int unitAmount = 0;
	private int unitID;
	public Unit() {
		unitID = unitAmount;
		unitAmount++;
	}
	public int getUnitID() {
		return unitID;
	}
	public int getUnitAmount() {
		return unitAmount;
	}
}
