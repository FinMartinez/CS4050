/**
 * Project Name: Recursive Palindrome
 * File Name: recursive_palindrome.java
 * Author: Fin Martinez, with help from ChatGPT and CoPilot
 * Date: 9 September 2024
 * Description: [Brief description of the purpose and functionality of the class or file]
 * Version: 1.0
 */

 public class RecursivePalindrome {
    public static void main(String[] args) {
        //Preliminary test cases:
        String[] testStrings = {"racecar", "hello", "madam", "nurses run", "a", "ab", "aa", "aba", "abba", "abcba"};

        for (String testString : testStrings) {
            long startTime = System.currentTimeMillis(); // Start time

            boolean isPalindrome = isPalindrome(testString);

            long endTime = System.currentTimeMillis(); // End time
            long duration = endTime - startTime; // Calculate duration

            System.out.println("Is \"" + testString + "\" a palindrome? " + isPalindrome);
            System.out.println("Processing time: " + duration + " milliseconds");
        }
    }

    /**
     * Checks if the given string is a palindrome using recursion.
     *
     * @param str The string to be checked.
     * @return true if the string is a palindrome, false otherwise.
     */

    public static boolean isPalindrome(String str) {
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

    private static boolean isPalindromeRecursive(String str) {
        
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