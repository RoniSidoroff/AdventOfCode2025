//
// Day two of Advent of Code 2025
// Part 1 and 2 solved. Finding repeating sequences of various lengths from a number
//

package DayTwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTwo {

    private static List<String> ranges = new ArrayList<String>();

    public static void main(String[] args) {
        readCommmandsFromFile();
        splitAndCheck();
        splitAndCheckPart2();
    }

    private static void readCommmandsFromFile() {

        // File path to ranges file
        // Assuming commands are in a ranges.txt file and the file is located in the same directory
        File commandFile = new File(System.getProperty("user.dir") + "\\src\\DayTwo\\ranges.txt");

        // Read the ranges file, which contains one line of comma separated values
        try (Scanner reader = new Scanner(commandFile)) {
            // Read the csv and split it to array
            String csvString = reader.nextLine();
            ranges = Arrays.asList(csvString.split(",", -1));
        } catch (FileNotFoundException e) {
            System.out.println("Error occured while reading file:");
            e.printStackTrace();
        }
    }

    private static void splitAndCheck() {
        
        long lowLimit;
        long highLimit;
        long sum = 0;

        for(int i = 0; i < ranges.size(); i++) {
            // Get the range
            String [] range  = ranges.get(i).split("-", -1);
            lowLimit = Long.parseLong(range[0]);
            highLimit = Long.parseLong(range[1]);
            // Check numbers in the range
            for (long currentNumber = lowLimit; currentNumber <= highLimit; currentNumber++) {
                // check if the number can be split in two parts evenly
                String numberString = String.valueOf(currentNumber);
                if (numberString.length() % 2 == 0) {
                    // split into two
                    int mid = numberString.length() / 2;
                    String [] parts = {numberString.substring(0, mid), numberString.substring(mid)};
                    if (parts[0].equals(parts[1])) {
                        // halves are equal, thus invalid IDs, add to sum
                        sum += Long.parseLong(numberString);
                    }
                }
            }
        }
        // Print the sum, which is the answer to part 1 task of day two
        System.out.println("Sum of all invalid IDs: " + sum);
    }

    private static void splitAndCheckPart2() {
        
        long lowLimit;
        long highLimit;
        long sum = 0;

        for(int i = 0; i < ranges.size(); i++) {
            // Get the range
            String [] range  = ranges.get(i).split("-", -1);
            lowLimit = Long.parseLong(range[0]);
            highLimit = Long.parseLong(range[1]);
            // Check numbers in the range
            for (long currentNumber = lowLimit; currentNumber <= highLimit; currentNumber++) {
                // Matching repeating sequences of numbers using regex
                String numberString = String.valueOf(currentNumber);
                Pattern pattern = Pattern.compile("^(\\d+?)\\1+$");
                Matcher matcher = pattern.matcher(numberString);
                while (matcher.find()) {
                    // if sequences are found, add to sum
                    sum += currentNumber;
                }
            }
        }
        // Print the sum, which is the answer to part 2 task of day two
        System.out.println("Sum of all invalid IDs in part 2: " + sum);
    }

}
