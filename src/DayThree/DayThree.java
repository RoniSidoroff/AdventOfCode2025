//
// Day three of Advent of Code 2025
// Both parts solved. Getting correct joltage from battery banks without changing the order of batteries.
// Two batteries in part 1 (findJoltage) and 12 batteries in part 2 (findMaxJoltage)
//

package DayThree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayThree {

    private static List<String> batteryBanks = new ArrayList<String>();


    public static void main(String[] args) {
        readCommmandsFromFile();
        findJoltage();
        findMaxJoltage();
    }

    private static void findJoltage() {
        int sum = 0;
        int number = 0;

        // Go through battery banks
        for(int k = 0; k < batteryBanks.size(); k++) {
            String bank = batteryBanks.get(k);
            // Get the largest number
            for(int i = 0; i<bank.length(); i++) {
                for(int j = i + 1; j<bank.length(); j++) {
                    // Form the number
                    String digits = String.valueOf(bank.charAt(i)) + String.valueOf(bank.charAt(j));
                    // Check if larger
                    if (Integer.parseInt(digits) > number) {
                        number = Integer.parseInt(digits);
                    }
                }
            }
            // Add the largest found number to the sum and reset
            sum = sum + number;
            number = 0;
        }

        // Print the answer
        System.out.println("Total output joltage is: " + sum);
    }

    private static void findMaxJoltage() {
        long sum = 0;
        int tempDigit = 0;
        int currentDigit = 0;

        // Go through battery banks
        for(int k = 0; k < batteryBanks.size(); k++) {
            String bank = batteryBanks.get(k);
            String digits = "";
            int remainingDigits = 12;
            int start= 0;
            // Loop to find all 12 digits
            while (remainingDigits > 0) {
                // Start searching greatest valid digit
                for(int i = start; i < bank.length(); i++) {
                    // considered digit as a substring 
                    tempDigit = Integer.parseInt(bank.substring(i, i+1));
                    // Is digit candidate greater or equal and can the rest of the digits be gathered from the right side?
                    if (tempDigit > currentDigit && bank.length()-i >= remainingDigits) {
                        // if valid digit is 9, there is no greater number candidate to consider, end current loop
                        if (tempDigit == 9) {
                            start = i+1;
                            currentDigit = tempDigit;
                            break;
                        } else {
                            // Otherwise adjust start and currentDigit to the current best option and continue the loop
                            start = i+1;
                            currentDigit = tempDigit;
                        }
                    }
                }
                // Add the largest found valid digit to digits
                digits = digits + String.valueOf(currentDigit);
                currentDigit = 0;
                remainingDigits--;
            }
            // Add the largest found number to the sum and reset
            sum = sum + Long.parseLong(digits);
        }

        // Print the answer
        System.out.println("Total maximum output joltage is: " + sum);
    }

    private static void readCommmandsFromFile() {

        // File path to batteries file
        // Assuming data are in a batteries.txt file and the file is located in the same directory
        File commandFile = new File(System.getProperty("user.dir") + "\\src\\DayThree\\batteries.txt");


        // Read the batteries file line by line and add battery banks to array
        try (Scanner reader = new Scanner(commandFile)) {

            while (reader.hasNextLine()) {
                // Put the banks to the array
                batteryBanks.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured while reading file:");
            e.printStackTrace();
        }
    }
}
