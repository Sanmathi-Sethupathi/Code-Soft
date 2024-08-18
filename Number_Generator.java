package codesoft;
import java.util.*;

public class Number_Generator {
    private static final int INITIAL_MAX_ATTEMPTS = 5;
    private static int maxAttempts = INITIAL_MAX_ATTEMPTS;
    private static int score = 0;
    private static List<String> leaderboard = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;
        String playerName;
        
        System.out.print("Enter your name: ");
        playerName = scanner.nextLine();
        
        do {
            playGame(scanner, playerName);
            System.out.println("Your current score: " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
            if (playAgain) {
                maxAttempts++; // Increase difficulty each round by reducing attempts
            }
        } while (playAgain);
        
        System.out.println("Thanks for playing! Final score: " + score);
        updateLeaderboard(playerName, score);
        displayLeaderboard();
        
        scanner.close();
    }
    
    private static void playGame(Scanner scanner, String playerName) {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int attempts = 0;
        
        System.out.println(playerName + ", guess the number between 1 and 100. You have " + maxAttempts + " attempts.");
        
        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;
            
            if (userGuess == numberToGuess) {
                System.out.println(" Congratulations! You've guessed the correct number in " + attempts + " attempts.");
                score += (maxAttempts - attempts + 1) * 10; // More points for fewer attempts
                return;
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Too low! Try again.");
            }
        }
        
        System.out.println("Sorry, you've used all your attempts. The correct number was: " + numberToGuess);
    }
    
    private static void updateLeaderboard(String playerName, int playerScore) {
        leaderboard.add(playerName + ": " + playerScore + " points");
    }
    
    private static void displayLeaderboard() {
        System.out.println(" Leaderboard:");
        leaderboard.sort(Collections.reverseOrder(Comparator.comparingInt(score -> Integer.parseInt(score.split(": ")[1].replace(" points", "")))));
        for (String entry : leaderboard) {
            System.out.println(entry);
        }
    }
}
