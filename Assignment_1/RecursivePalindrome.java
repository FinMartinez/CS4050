/**
 * Project Name: Recursive Palindrome
 * File Name: recursive_palindrome.java
 * Author: Fin Martinez, with help from ChatGPT and CoPilot
 * Date: 9 September 2024
 * Description: [Brief description of the purpose and functionality of the class or file]
 * Linear Recursion:

    Linear recursion occurs when a function calls itself once in each recursive step, 
    typically reducing the problem size by one (or in this case, by removing the first and last characters).
    In your isPalindromeRecursive function, each recursive call is made on a substring that removes the first and last characters of the string (str.substring(1, length - 1)), 
    which reduces the problem size by two characters in each step.
    The recursion continues until the base case is reached (the length of the string is 1 or less), which results in a single linear chain of calls.
 * Version: 1.0
 */

 import java.util.Scanner;

     /* program user proofing/validation, I guess prompt for input?
     * Define if the recursion is mutual, linear, or tail
     * minimum 2 classes, one for the main and one for the recursive function, one for the menu as well?
     */

 public class RecursivePalindrome {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PalindromeChecker palindromeChecker = new PalindromeChecker();
        TestCaseRunner testCaseRunner = new TestCaseRunner();

        boolean exitProgram = false;

        while (!exitProgram) {
            //User Menu
            System.out.println("\nRecursive Palindrome Checker");
            System.out.println("1. Run test cases");
            System.out.println("2. Check a custom string");
            System.out.println("3. Exit");
            System.out.println("Enter your choice (1-3): ");

            String userSelection = scanner.nextLine();

            switch (userSelection) {
                case "1":
                    testCaseRunner.runTestCases(palindromeChecker);
                    break;
                case "2":
                    System.out.println("Enter a string to check if it's a palindrome: ");
                    String userInput = scanner.nextLine();

                    long startTime = System.currentTimeMillis(); // Start time

                    boolean isPalindrome = palindromeChecker.isPalindrome(userInput);

                    long endTime = System.currentTimeMillis(); // End time
                    long duration = endTime - startTime; // Calculate duration

                    System.out.println("Is \"" + userInput + "\" a palindrome? " + isPalindrome);
                    System.out.println("Processing time: " + duration + " milliseconds");
                    break;

                case "3":
                    exitProgram = true;
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}


class PalindromeChecker {
    
    /**
     * Checks if the given string is a palindrome using recursion.
     *
     * @param str The string to be checked.
     * @return true if the string is a palindrome, false otherwise.
     */

    public boolean isPalindrome(String str) {
        // Remove non-alphanumeric characters and convert to lowercase
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return isPalindromeRecursive(str);
    }

    /**
     * Recursively checks if the given string is a palindrome.
     *
     * @param str The string to be checked.
     * @return true if the string is a palindrome, false otherwise.
     */

    private boolean isPalindromeRecursive(String str) {
        
        int length = str.length();

        // Base case:
        if (length <= 1) {
            return true;
        }
        if (str.charAt(0) != str.charAt(length - 1)) {
            return false;
        }

        return isPalindromeRecursive(str.substring(1, length - 1));

    }
 }

class TestCaseRunner {

    /**
     * Runs the preliminary test cases.
     * @param palindromeChecker An instance of PalindromeChecker to check each test case.
     */
    public void runTestCases(PalindromeChecker palindromeChecker) {
        //Preliminary test cases:
        String[] testStrings = {"racecar", "hello", "madam", "nurses run", "a", "ab", "aa", "aba", "abba", "abcba"};

        for (String testString : testStrings) {
            long startTime = System.currentTimeMillis(); // Start time

            boolean isPalindrome = palindromeChecker.isPalindrome(testString);

            long endTime = System.currentTimeMillis(); // End time
            long duration = endTime - startTime; // Calculate duration

            System.out.println("Is \"" + testString + "\" a palindrome? " + isPalindrome);
            System.out.println("Processing time: " + duration + " milliseconds");
        }
    }
}