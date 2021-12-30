package ibf29dec;

import java.util.Scanner;

public class CodeBreaker {
	private String codeAttempt;
	private Scanner myScan = new Scanner(System.in);

	public CodeBreaker() {
	}
	public String getCodeAttempt() {
		return this.codeAttempt;
	}
	public String codeAttempt() {
		this.codeAttempt = myScan.nextLine();
		if (codeAttempt.contains("[,-_*/\\]")) {
			System.out.println("Please ommit and special characters and seperate pegs by space only:");
			this.codeAttempt = myScan.nextLine();
		}
		return codeAttempt;
	}

}
