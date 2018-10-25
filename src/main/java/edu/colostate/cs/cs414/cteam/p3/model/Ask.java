package edu.colostate.cs.cs414.cteam.p3.model;
import java.util.Scanner;

public class Ask {

	public static Move ask(int player, Tile[][] board, String message) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Player " + player + ": \n" + message);
		System.out.println("To move, type \"x(from),y(from) to x(to),y(to)\"");
		System.out.println("To attack, type \"x(from),y(from) attack x(to),y(to)\"");
		// format of an input should be "x,y attack x,y" or "x,y to x,y"
		String[] line = scan.nextLine().split(",|\\s");
		if (line.length != 5) {
			System.out.println("Please insert a valid command!");
			return null;
		}
		int fromX, fromY, toX, toY = 0;
		try {
			fromX = Integer.parseInt(line[0]);
			fromY = Integer.parseInt(line[1]);
			toX = Integer.parseInt(line[3]);
			toY = Integer.parseInt(line[4]);
		} catch (NumberFormatException e) {
			System.out.println("Please insert a valid command!");
			return null;
		}

		if (line[2].equalsIgnoreCase("attack")) {
			return new Move(fromX, fromY, toX, toY, true);
		} else if (line[2].equalsIgnoreCase("to")) {
			return new Move(fromX, fromY, toX, toY, false);
		} else {
			System.out.println("Please insert a valid command!");
			return null;
		}
	}
}
