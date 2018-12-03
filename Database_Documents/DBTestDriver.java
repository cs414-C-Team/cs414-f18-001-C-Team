import java.util.Scanner;

public class DBTestDriver {

	public static void main(String[] args) {
		Database db = new Database();

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
