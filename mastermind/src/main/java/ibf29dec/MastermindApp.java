package ibf29dec;

import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Map;

public class MastermindApp {
    public static void main(String[] args) {
        HashMap<String, Integer> points = new HashMap<>(
                Map.of(
                        "Codemaker", 0,
                        "Codebreaker", 0));
        CodeMaker myCodeMaker = new CodeMaker();
        CodeBreaker mycodeBreaker = new CodeBreaker();

        System.out.println("Welcome to Mastermind! THe codemaker has been expecting you");
        while (true) {
            System.out.println(
                    "The codemaker has specially prepared a 4-coloured peg code for you, do you think you can break it?");
            System.out.println(
                    "Please enter your codebreak attempt consisting of 4 coloured pegs seperated by a space, Multiple pegs of same colour are allowed.\nAccepted colours: \nRD: Red\nBL: Blue\nGN: Green\nYW: Yellow\nBL: Black\nWH: White\n");
            System.out.println("You have 10 attempts, good luck!");
            for (int i = 0; i < 10; i++) {
                String codeAttempt = mycodeBreaker.codeAttempt();
                String result = "";

                try {
                    result = myCodeMaker.checkAttempt(codeAttempt);
                } catch (NullPointerException e) {
                    System.out.println("You entered an incorrect value, please re-enter your code attempt");
                    codeAttempt = mycodeBreaker.codeAttempt();
                    result = myCodeMaker.checkAttempt(codeAttempt);
                }
                if (result.equals("BK BK BK BK ")) {
                    System.out.println(result);
                    points.put("Codebreaker", points.get("Codebreaker") + 1);
                    System.out.println("Congratulations! You have broken the codemaker's code!");
                    System.out.println(
                            "If you wish to try another code, enter continue or if you want to quit, enter exit");
                    mycodeBreaker.codeAttempt();
                    break;
                } else if (i == 9) {
                    System.out.println(result);
                    points.put("Codemaker", points.get("Codemaker") + 1);
                    System.out.println("Sorry, you have run out of tries to figure out the codemaker's code!");
                    System.out.printf("This was the codemakers code!: %s", myCodeMaker.getCode());
                    System.out.println(
                            "If you wish to try another code, enter continue or if you want to quit, enter exit");
                    mycodeBreaker.codeAttempt();
                    break;
                } else {
                    System.out.println(result);
                }
            }
            if (mycodeBreaker.getCodeAttempt().equalsIgnoreCase("continue")) {
                myCodeMaker = new CodeMaker();
                continue;
            } else if (mycodeBreaker.getCodeAttempt().equalsIgnoreCase("exit")) {
                break;
            }
        }
        System.out.printf("Total Scores:\nCodeMaker: %d\nCodeBreaker: %d\n", points.get("Codemaker"),
                points.get("Codebreaker"));
        int highestPoints = (Math.max(points.get("Codemaker"), points.get("Codebreaker")));
        String winner = (String) points.keySet().stream().filter(s -> points.get(s).equals(highestPoints)).toArray()[0];
        System.out.printf("%s wins with %d points!\n", winner, highestPoints);
        System.out.println("Thank you for playing! Stop by again anytime!");

    }
}
