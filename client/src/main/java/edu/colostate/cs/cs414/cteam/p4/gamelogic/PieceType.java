package edu.colostate.cs.cs414.cteam.p4.gamelogic;

public enum PieceType {
	RAT('R'), CAT('C'), WOLF('W'), DOG('D'), LEOPARD('H'), TIGER('T'), LION('I'), ELEPHANT('E');

	private final char print;

	private PieceType(final char print) {
		this.print = print;
	}

	public String toString() {
		return ("" + print).toLowerCase();
	}
}
