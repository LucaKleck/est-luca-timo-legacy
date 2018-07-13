package info;

public enum ResourceTypes {
	Money("Money",1),
	Wood("Wood",2),
	Stone("Stone",3),
	Metal("Metal",4),
	ManaStones("ManaStones",5);
	
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
