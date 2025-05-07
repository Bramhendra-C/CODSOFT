/*
Hey there! Welcome to the Guessing Game :)

-- Round 1 --
I've picked a number between 1 and 100. Try to guess it!
Your guess: 50
Oops, too low!
Tries left: 4
Your guess: 23
Oops, too low!
Tries left: 3
Your guess: 90
Too high!
Tries left: 2
Your guess: 70
Too high!
Tries left: 1
Your guess: 65
Too high!
No more tries left!
No worries! The number was: 51
Wanna play again? (yes/no): no

Thanks for playing!
Total Rounds: 1
Final Score: 0
*/
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int totalScore = 0;
        int roundsPlayed = 0;
        final int MAX_TRIES = 5;											//Minimum chances is 5

        System.out.println("Hey there! Welcome to the Guessing Game :)");

        boolean keepPlaying = true;

        while (keepPlaying) {												
            int secretNumber = rand.nextInt(100) + 1; // 1 to 100
            int triesLeft = MAX_TRIES;
            boolean isGuessed = false;

            roundsPlayed++;
            System.out.println("\n-- Round " + roundsPlayed + " --");
            System.out.println("I've picked a number between 1 and 100. Try to guess it!");

            while (triesLeft > 0) {
                System.out.print("Your guess: ");
                int guess = input.nextInt();
                triesLeft--;

                if (guess == secretNumber) {
                    isGuessed = true;
                    int earnedPoints = triesLeft + 1;
                    totalScore += earnedPoints;
                    System.out.println("Nice! You got it in " + (MAX_TRIES - triesLeft) + " tries.");
                    System.out.println("Points this round: " + earnedPoints);
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("Oops, too low!");
                } else {
                    System.out.println("Too high!");
                }

                if (triesLeft > 0) {		
                    System.out.println("Tries left: " + triesLeft);
                } else {
                    System.out.println("No more tries left!");
                }
            }

            if (!isGuessed) {																	//Player not guessed in his chances then the Secret Number will be display  
                System.out.println("No worries! The number was: " + secretNumber);
            }

            System.out.print("Wanna play again? (yes/no): ");									//Checking if player want play again or not
            String userReply = input.next().toLowerCase();
            if (!userReply.equals("yes")) {
                keepPlaying = false;
            }
        }

        System.out.println("\nThanks for playing!");
        System.out.println("Total Rounds: " + roundsPlayed);
        System.out.println("Final Score: " + totalScore);

        input.close();
    }
}
