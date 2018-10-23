
public enum TileType {
	DEN('$'), BLANK(' '), WATER('/'), TRAP1('!'), TRAP2('!');
	
	private final char print;
	private TileType(final char print) {
		this.print = print;
	}
	
	public String toString() {
		return "" + print;
	}
}
