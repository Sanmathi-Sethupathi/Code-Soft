package codesoft;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizGame {

    private static String[][] questions = {
            {"What is the capital of France?", "A. Paris", "B. Rome", "C. Berlin", "D. Madrid", "A"},
            {"Who wrote 'To Kill a Mockingbird'?", "A. Harper Lee", "B. Mark Twain", "C. J.K. Rowling", "D. Jane Austen", "A"},
            {"What is the largest planet in our solar system?", "A. Earth", "B. Mars", "C. Jupiter", "D. Saturn", "C"},
            {"What is the chemical symbol for water?", "A. H2O", "B. CO2", "C. O2", "D. NaCl", "A"},
            {"Who painted the Mona Lisa?", "A. Vincent van Gogh", "B. Pablo Picasso", "C. Leonardo da Vinci", "D. Claude Monet", "C"}
    };

    private static int score = 0;
    private static int questionIndex = 0;
    private static int timeLimitPerQuestion = 10; // seconds
    private static boolean answered = false;
    private static boolean timedOut = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (questionIndex = 0; questionIndex < questions.length; questionIndex++) {
            presentQuestion(scanner);
        }

        displayResults();
        scanner.close();
    }

    private static void presentQuestion(Scanner scanner) {
        System.out.println("\nQuestion " + (questionIndex + 1) + ":");
        for (int i = 0; i < 5; i++) {
            System.out.println(questions[questionIndex][i]);
        }

        Timer timer = new Timer();
        answered = false;
        timedOut = false;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered) {
                    System.out.println("\nTime's up!");
                    System.out.println("The correct answer was: " + questions[questionIndex][5]);
                    timedOut = true;
                    timer.cancel();
                }
            }
        }, timeLimitPerQuestion * 1000);

        System.out.print("Enter your answer (A, B, C, D): ");
        String userAnswer = scanner.next().toUpperCase();
        answered = true;
        timer.cancel();

        if (!timedOut) {
            checkAnswer(userAnswer);
        }
    }

    private static void checkAnswer(String userAnswer) {
        String correctAnswer = questions[questionIndex][5];

        if (userAnswer.equals(correctAnswer)) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer was " + correctAnswer + ".");
        }
    }

    private static void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score is: " + score + " out of " + (questions.length - (timedOut ? 1 : 0)));

        System.out.println("\nSummary:");
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i][0]);
            System.out.println("Correct answer: " + questions[i][5]);
            System.out.println();
        }
    }
}
