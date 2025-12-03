//
// Day one of Advent of Code 2025
// Calculating number of times dial hits 0 (or ends in 0) and reading commands from a text file.
//

package DayOne;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DayOne {

    // Change if length changes
    private static final int COMMAND_LENGTH = 4107;
    private static String [] commandArray = new String[COMMAND_LENGTH];

    private static void readCommmandsFromFile() {

        // File path to commands file
        // Assuming commands are in a commands.txt file and the file is located in the same directory
        File commandFile = new File(System.getProperty("user.dir") + "\\src\\DayOne\\commands.txt");
        // Index for array
        int index = 0;

        // Read the commands file line by line and add commands to array
        try (Scanner reader = new Scanner(commandFile)) {

            while (reader.hasNextLine()) {
                // Put the commands to the array and increment index
                commandArray[index] = reader.nextLine();
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured while reading file:");
            e.printStackTrace();
        }
    }

    private static int calculateZerosPart1() {

        // Dial starts at 50
        int dial = 50;
        int zeroCounter = 0;

        // Go through commands: Part 1
        for(int i = 0; i < commandArray.length; i++) {
            // Check the command for left of right
            if (commandArray[i].charAt(0) == 'L') {
                // left side
                int move = Integer.parseInt(commandArray[i].substring(1, commandArray[i].length()));
                dial = dial - move;
                // if dial went negative, add 100s until not negative
                while (dial < 0) {
                    dial += 100;
                }
                // check if dial is 0
                if (dial == 0) {
                    // if looped and landed of zero, don't add same zero twice
                    zeroCounter++;
                }
                // reset
            } else {
                // right side
                int move = Integer.parseInt(commandArray[i].substring(1, commandArray[i].length()));
                dial = dial + move;
                // if dial went over 99, subtract 100s until under 99
                while (dial > 99) {
                    dial -= 100;
                }
                // check if dial is 0
                if (dial == 0) {
                    // Don't add the same zero twice if looped
                    zeroCounter++;
                }
            }
        }
        return zeroCounter;
    }

    private static int calculateZerosPart2() {

        // Dial starts at 50
        int dial = 50;
        int zeroCounter = 0;

        // Go thought commands: Part 2, method 0x434C49434B
        for(int i = 0; i < commandArray.length; i++) {
            if (commandArray[i].charAt(0) == 'L') {
                // method 0x434C49434B left side
                // Get move
                int move = Integer.parseInt(commandArray[i].substring(1, commandArray[i].length()));
                while (move > 0) {
                    // Decrement dial and move
                    dial--;
                    move--;
                    // Calculate times zero is met and loop around
                    if (dial == 0) {
                        zeroCounter++;
                    } else if (dial < 0) {
                        dial = 99;
                    }
                }
            } else {
                // method 0x434C49434B right side
                // Get move
                int move = Integer.parseInt(commandArray[i].substring(1, commandArray[i].length()));
                while (move > 0) {
                    // Increment dial, decrement move
                    dial++;
                    move--;
                    // Calculate times zero is met and loop around
                    if (dial == 0) {
                        zeroCounter++;
                    } else if (dial > 99) {
                        dial = 0;
                        zeroCounter++;
                    }
                }
            }
        }
        return zeroCounter;

    }

    public static void main(String[] args) {
        // Read the commands to an array
        readCommmandsFromFile();
        int zeroCounter = 0;
        // Part 1
        zeroCounter = calculateZerosPart1();
        System.out.println("Count of zeros calculated in part 1: " + zeroCounter);
        zeroCounter = 0;
        // Part 2
        zeroCounter = calculateZerosPart2();
        System.out.println("Count of zeros calculated in part 2: " + zeroCounter);
    }  
}
