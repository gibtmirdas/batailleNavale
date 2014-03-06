package v2;

public enum CartesTypes {

	DMG("DMG"), MVT("MVT"), RADAR("RADAR");
	private String name;

	private CartesTypes(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
