package edu.colostate.cs.cs414.cteam.p3.model;

public enum TileType {
	DEN1('$'), DEN2('$'), BLANK(' '), WATER('/'), TRAP('!');
	
	private final char print;
	private TileType(final char print) {
		this.print = print;
	}
	
	public String toString() {
		return "" + print;
	}
}
