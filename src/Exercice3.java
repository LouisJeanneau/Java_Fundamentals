/*
Write a program which randomizes a number between 0-100.
Then the program asks the user what is the number.
If the user does not have guessed, the application displays if the correct number is higher or lower than proposed by the user.
If the user guessed correctly, the applications displays how many tries have been made and asks the user if he wants to play again.
 */

import java.util.Random;
import java.util.Scanner;

public class Exercice3 {
    public static void main(String[] args) {
        String a;
        Scanner entree = new Scanner(System.in);  // Create a Scanner object
        do{
            OneGame(entree);
            System.out.println("Do you want to play again ? If so, type <y>");
            do {
                a = entree.nextLine();
            }while (a == "");
        }while (a.equalsIgnoreCase("y"));
        System.out.println("Bye");

    }

    public static void OneGame(Scanner scanner){

        Random random = new Random();
        int answer = random.nextInt(101);
        int guess = -1;
        int numberOfTries = 0;
        while (guess != answer) {
            System.out.println("Please enter a number");
            try {
                guess = scanner.nextInt();  // Read user input
            }
            catch(Exception e) {
                System.out.println("Not an integer");
                scanner.nextLine();
                continue;
            }

            if(guess < 0 || guess > 100){
                System.out.println("Number out of bonds");
                continue;
            }
            System.out.println("You typed : " + guess);  // Output user input
            numberOfTries += 1;
            if(guess < answer){
                System.out.println("The answer is a higher number");
            } else if (guess > answer) {
                System.out.println("The answer is a lower number");
            }
        }
        System.out.println("GG. You guessed the number in " + numberOfTries + " guesses.");

    }
}

