package units;

public abstract class Controlable extends Unit {

	public Controlable(int range, int healthPoints) {
		super(range, healthPoints);
	}
	@SuppressWarnings("unused")
	private void moveTo(int x, int y) {
		
	}
	public void tryMoveTo(int x, int y) {
		// check for in range
		// call moveTo
	}
	
}
