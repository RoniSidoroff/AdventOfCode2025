//
// Day four of Advent of Code 2025
// Both parts solved. Counting the number of paper rolls that can be accessed with a forklift in part 1 (checkForAdjacency)
// and removing those rolls until no rolls can be removed anymore in part 2 (checkForAdjacencyAndRemove).
//

package DayFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayFour {

    private static final int MATRIX_DIMENSION = 140;
    private static char[][] paperMatrix = new char[MATRIX_DIMENSION][MATRIX_DIMENSION];


    public static void main(String[] args) {
        readCommmandsFromFile();
        checkForAdjacency();
        checkForAdjacencyAndRemove();
    }

    private static void checkForAdjacency() {

        int rollNumber = 0;
        int adjacencyCount = 0;
        boolean upValid = false;
        boolean downValid = false;
        boolean leftValid = false;
        boolean rightValid = false;

        // Loop for rows
        for (int i = 0; i < MATRIX_DIMENSION; i++) {
            // Loop for columns (elements)
            for (int j = 0; j < MATRIX_DIMENSION; j++) {
                // If roll of paper
                if (paperMatrix[i][j] == '@') {
                    // Define up, down, left, right
                    int up = i - 1;
                    int down = i + 1;
                    int left = j - 1;
                    int right = j + 1;
                    // Check for upper row
                    if (up >= 0) {
                        upValid = true;
                        // Check "up" element
                        if (paperMatrix[up][j] == '@') {
                            adjacencyCount++;
                        }
                    }
                    // Check for lower row
                    if (down < MATRIX_DIMENSION) {
                        downValid = true;
                        // Check "down" element
                        if (paperMatrix[down][j] == '@') {
                            adjacencyCount++;
                        }
                    }
                    // Check left
                    if (left >= 0) {
                        leftValid = true;
                        // Left element
                        if (paperMatrix[i][left] == '@') {
                            adjacencyCount++;
                        }
                    }
                    // Check right
                    if (right < MATRIX_DIMENSION) {
                        rightValid = true;
                        if (paperMatrix[i][right] == '@') {
                            adjacencyCount++;
                        }
                    }
                    // Northwest
                    if (upValid && leftValid) {
                        if (paperMatrix[up][left] == '@') {
                            adjacencyCount++;
                        }
                    }
                    // Northeast
                    if (upValid && rightValid) {
                        if (paperMatrix[up][right] == '@') {
                            adjacencyCount++;
                        }
                    }
                    // Southwest
                    if (downValid && leftValid) {
                        if (paperMatrix[down][left] == '@') {
                            adjacencyCount++;
                        }
                    }
                    // Southeast
                    if (downValid && rightValid) {
                        if (paperMatrix[down][right] == '@') {
                            adjacencyCount++;
                        }
                    }
                    // Finally check if roll of paper can be accessed by forklift and reset
                    if (adjacencyCount < 4) {
                        rollNumber++;
                        upValid = false;
                        downValid = false;
                        leftValid = false;
                        rightValid = false;
                        adjacencyCount = 0;
                    } else {
                        upValid = false;
                        downValid = false;
                        leftValid = false;
                        rightValid = false;
                        adjacencyCount = 0;
                    }
                }
            }
        }
        // print the number of rolls that can be moved
        System.out.println("Number of rolls that can be accessed by forklift: " + rollNumber);
    }

    private static void checkForAdjacencyAndRemove() {

        int removedRollNumber = 0;
        int totalRemoved = 0;
        int adjacencyCount = 0;
        boolean upValid = false;
        boolean downValid = false;
        boolean leftValid = false;
        boolean rightValid = false;

        // Do at least once
        do {
            // Set removed rolls to 0 at start
            removedRollNumber = 0;
            // Loop for rows
            for (int i = 0; i < MATRIX_DIMENSION; i++) {
                // Loop for columns (elements)
                for (int j = 0; j < MATRIX_DIMENSION; j++) {
                    // If roll of paper
                    if (paperMatrix[i][j] == '@') {
                        // Define up, down, left, right
                        int up = i - 1;
                        int down = i + 1;
                        int left = j - 1;
                        int right = j + 1;
                        // Check for upper row
                        if (up >= 0) {
                            upValid = true;
                            // Check "up" element
                            if (paperMatrix[up][j] == '@') {
                                adjacencyCount++;
                            }
                        }
                        // Check for lower row
                        if (down < MATRIX_DIMENSION) {
                            downValid = true;
                            // Check "down" element
                            if (paperMatrix[down][j] == '@') {
                                adjacencyCount++;
                            }
                        }
                        // Check left
                        if (left >= 0) {
                            leftValid = true;
                            // Left element
                            if (paperMatrix[i][left] == '@') {
                                adjacencyCount++;
                            }
                        }
                        // Check right
                        if (right < MATRIX_DIMENSION) {
                            rightValid = true;
                            if (paperMatrix[i][right] == '@') {
                                adjacencyCount++;
                            }
                        }
                        // Northwest
                        if (upValid && leftValid) {
                            if (paperMatrix[up][left] == '@') {
                                adjacencyCount++;
                            }
                        }
                        // Northeast
                        if (upValid && rightValid) {
                            if (paperMatrix[up][right] == '@') {
                                adjacencyCount++;
                            }
                        }
                        // Southwest
                        if (downValid && leftValid) {
                            if (paperMatrix[down][left] == '@') {
                                adjacencyCount++;
                            }
                        }
                        // Southeast
                        if (downValid && rightValid) {
                            if (paperMatrix[down][right] == '@') {
                                adjacencyCount++;
                            }
                        }
                        // Finally check if roll of paper can be accessed by forklift, remove and reset
                        if (adjacencyCount < 4) {
                            removedRollNumber++;
                            totalRemoved++;
                            upValid = false;
                            downValid = false;
                            leftValid = false;
                            rightValid = false;
                            adjacencyCount = 0;
                            // remove roll
                            paperMatrix[i][j] = '.';
                        } else {
                            upValid = false;
                            downValid = false;
                            leftValid = false;
                            rightValid = false;
                            adjacencyCount = 0;
                        }
                    }
                }
            }
        } while (removedRollNumber != 0);
        // print the number of rolls that can be moved
        System.out.println("Number of rolls that were removed: " + totalRemoved);
    }

    private static void readCommmandsFromFile() {

        // File path to paper file
        // Assuming data are in a paper.txt file and the file is located in the same directory
        File commandFile = new File(System.getProperty("user.dir") + "\\src\\DayFour\\paper.txt");

        // Read the paper file and form a matrix from the data
        try (Scanner reader = new Scanner(commandFile)) {
            int i = 0;
            while(reader.hasNextLine()) {
                String rowString = reader.nextLine();
                paperMatrix[i] = rowString.toCharArray();
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occured while reading file:");
            e.printStackTrace();
        }
    }
    
}