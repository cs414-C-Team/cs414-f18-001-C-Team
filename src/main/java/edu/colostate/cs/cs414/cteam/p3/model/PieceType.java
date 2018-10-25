package edu.colostate.cs.cs414.cteam.p3.model;

public enum PieceType {
	RAT('R'), CAT('C'), WOLF('W'), DOG('D'), LEOPARD('L'), TIGER('T'), LION('I'), ELEPHANT('E');

	private final char print;

	private PieceType(final char print) {
		this.print = print;
	}

	public String toString() {
		return "" + print;
	}
}
