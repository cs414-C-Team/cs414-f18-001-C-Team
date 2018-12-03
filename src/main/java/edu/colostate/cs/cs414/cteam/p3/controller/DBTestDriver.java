package edu.colostate.cs.cs414.cteam.p3.controller;
import java.util.Scanner;

public class DBTestDriver {

	public static void main(String[] args) {
		DatabaseController db = new DatabaseController();

		String input = "";
		System.out.println("Enter queries");
		while(true) {
			Scanner scanner = new Scanner(System.in);
			input = scanner.nextLine();
			if(input.equals("quit")) {
				break;
			}
			
			System.out.println(db.sendQuery(input));
		}
	}

}
