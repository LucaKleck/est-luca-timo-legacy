package info;

public enum ResourceTypes {
	Money("Money",1),
	Food("Food",2),
	Wood("Wood",3),
	Stone("Stone",4),
	Metal("Metal",5),
	ManaStones("ManaStones",6);
	
	private final int type;
	private final String name;
	ResourceTypes(String name, int type) {
		this.name = name;
		this.type = type;
	}
	public int getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	
}
