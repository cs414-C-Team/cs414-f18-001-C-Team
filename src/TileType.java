
public enum TileType {
	DEN('$'), BLANK(' '), WATER('/'), TRAP('!');
	
	private final char print;
	private TileType(final char print) {
		this.print = print;
	}
	
	public String toString() {
		return "" + print;
	}
}
