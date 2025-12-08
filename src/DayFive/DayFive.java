//
// Day five of Advent of Code 2025
// Both parts solved. Calculating if ID is in valid range in part 1 (searchForId)
// and calculating the number of valid IDs in given ranges in part 2 (getUniqueFreshIDs).
// Part 2 code is not tested enough and might be buggy for certain input data, but it works for the data in database.txt.
//

package DayFive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DayFive {

    private static List<String> ranges = new ArrayList<String>();
    private static List<String> idList = new ArrayList<String>();

    public static void main(String[] args) {
        readCommmandsFromFile();
        searchForId();
        getUniqueFreshIDs();
    }


    private static void searchForId() {

        long lowLimit;
        long highLimit;
        int freshSum = 0;

        // Go through IDs
        for (int j = 0; j < idList.size(); j++) {
            long id = Long.parseLong(idList.get(j));
            // Go through ranges
            for (int i = 0; i < ranges.size(); i++) {
                // limits
                lowLimit = Long.parseLong(ranges.get(i).split("-", -1)[0]);
                highLimit = Long.parseLong(ranges.get(i).split("-", -1)[1]);
                if (id >= lowLimit && id <= highLimit) {
                    freshSum++;
                    break;
                }
            }
        }
        // Print results
        System.out.println("Number of fresh ingredients: " + freshSum);
    }

    private static void getUniqueFreshIDs() {

        long lowLimit;
        long highLimit;
        long sumOfUniqueIds = 0;
        List<Long> lowerLimits = new ArrayList<>();
        List<Long> upperLimits = new ArrayList<>();

        // Get limits in lists
        for (int i = 0; i < ranges.size(); i++) {
            lowLimit = Long.parseLong(ranges.get(i).split("-", -1)[0]);
            highLimit = Long.parseLong(ranges.get(i).split("-", -1)[1]);
            lowerLimits.add(lowLimit);
            upperLimits.add(highLimit);
        }
        // Sort the lists
        Collections.sort(lowerLimits);
        Collections.sort(upperLimits);

        long currentLowLimit = lowerLimits.get(0);
        
        // Lower limit should never be bigger than upper limit
        // Compare lower(i+1) to upper(i) to figure out where new ranges begin
        for (int i = 1; i < lowerLimits.size(); i++) {
                if (lowerLimits.get(i) > upperLimits.get(i-1)) {
                    // Number of valid IDs in range
                    sumOfUniqueIds = sumOfUniqueIds + (upperLimits.get(i-1) - currentLowLimit) + 1;
                    // New lower limit for range
                    currentLowLimit = lowerLimits.get(i);
                }
        }
        // Last range
        sumOfUniqueIds = sumOfUniqueIds + (upperLimits.getLast() - currentLowLimit) + 1;
        // Print results
        System.out.println("Number of fresh ingredient IDs: " + sumOfUniqueIds);

    }

    private static void readCommmandsFromFile() {

        boolean idStart = false;

        // File path to database file
        // Assuming data is in a database.txt file and the file is located in the same directory
        File commandFile = new File(System.getProperty("user.dir") + "\\src\\DayFive\\database.txt");

        // Read the database file and get ranges and IDs
        try (Scanner reader = new Scanner(commandFile)) {
            while(reader.hasNextLine()) {
                String rowString = reader.nextLine();
                if (rowString.equals("")) {
                    // Start of IDs
                    idStart = true;
                    continue;
                }
                if (!idStart) {
                    // Add ranges
                    ranges.add(rowString);
                } else {
                    // Add IDs
                    idList.add(rowString);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured while reading file:");
            e.printStackTrace();
        }
    }



    
}
