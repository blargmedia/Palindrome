import java.util.ArrayList;
import java.util.Scanner;

/**
 * Problem: Implement a small java program that takes a string of arbitrary length and
 * returns the largest palindrome within the string that is greater than one character
 */

public class Palindrome {

    private ArrayList<String> foundPalindromes;

    public Palindrome (String s) {

        foundPalindromes = new ArrayList<>();

        findAllPalindromes(s);

        System.out.print("Longest Palindrome: ");
        if (foundPalindromes.isEmpty()) {
            System.out.println("None");
        }
        else {
            String longestPalindrome = findLongestPalindrome();
            System.out.println(longestPalindrome);
        }
    }

    /**
     * Iterates through the string in search of any palindromes.
     * If one is found, add it to the ArrayList.
     * @param s the string to search
     */
    public void findAllPalindromes(String s) {

        // assumption: we only want the letters, so we'll ignore any whitespace and punctuation
        String sane = sanitizeString(s);

        // given an input string of arbitrary length, we'll need to iterate through
        // it repeatedly to check all of its substrings for 'palindrome-ness'
        StringBuilder tmpS = new StringBuilder();
        for (int i = 0; i < sane.length(); i++) {
            tmpS.append(sane.charAt(i));
            for (int j = i+1; j < sane.length(); j++) {
                tmpS.append(sane.charAt(j));
                if (isPalindrome(tmpS.toString())) {
                    foundPalindromes.add(tmpS.toString());
                }
            }

            // clear the tmp string and proceed to the next
            int end = tmpS.length();
            tmpS.delete(0,end);
        }
    }

    /**
     * Searches the ArrayList for the longest palindrome
     * @return the longest palindrome
     */
    public String findLongestPalindrome() {

        int longest = 0;
        String longestString = "";
        for (String s : foundPalindromes) {
            if (s.length() > longest) {
                longestString = s;
                longest = s.length();
            }
        }
        return longestString;
    }

    /**
     * Takes a string and converts it to lowercase, then removes non-alpha characters
     * @param s the string to sanitize
     * @return the sanitized string
     */
    public String sanitizeString(String s) {
        String sl = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (sl.charAt(i) >= 'a' && sl.charAt(i) <= 'z') {
                sb.append(sl.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * Checks a string against its reverse to see if it is a palindrome
     * @param s the string to check
     * @return the result of the check
     */
    public boolean isPalindrome (String s) {
        StringBuilder sb = new StringBuilder(s);
        String srev = sb.reverse().toString();
        return s.equals(srev);
    }

    public static void main(String[] args) {

        System.out.print("Enter a string with more than one character: ");
        Scanner scin = new Scanner (System.in);
        String s = scin.nextLine();

        if (s.length() <= 1) {
            System.out.println("Input string too short.");
            return;
        }

        try {
            new Palindrome(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
