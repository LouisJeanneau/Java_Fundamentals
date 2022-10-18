/*
Write a program that checks in a user-supplied expression arithmetic, the parentheses are correctly matched.
The expression is given as a single string of characters and is composed using standard signs.
The program should display an appropriate message (matched/not matched).
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercice4 {
    public static void main(String[] args) {
        String a;
        Scanner entree = new Scanner(System.in);
        List<Character> charStr = new ArrayList<>();
        int count = 0;
        while (true){
            a = entree.nextLine();
            a.chars().forEach(y -> charStr.add((char) y));
            for (Character c:charStr) {
                if (c.equals('(')){
                    count+=1;
                } else if (c.equals(')') && count>0) {
                    count -= 1;
                } else if (c.equals(')')) {
                    count -=1;
                    break;
                }
            }
            if (count!=0){
                System.out.println("Incorrect match");
            }
            else {
                System.out.println("Good match");
            }
            count=0;
            charStr.clear();
        }


    }
}
