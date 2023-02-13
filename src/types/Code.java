package types;

import java.util.Arrays;
import java.util.Random;

public class Code {

    public static void main(String[] args) {
    }

    public static int sum(int[] numbers) {
        int sumOfNumbers = 0;
        for (int number : numbers) {
            sumOfNumbers += number;
        }
        return sumOfNumbers;
    }

    public static double average(int[] numbers) {
        int sumNumbers = sum(numbers);

        return sumNumbers / Double.valueOf(numbers.length);
    }


    public static Integer minimumElement(int[] integers) {

        if (integers == null) {
            return null;
        }
        Integer minElement = null;
        for (Integer number : integers) {
            if (minElement == null) {
                minElement = number;
            }
            if (number < minElement) {
                minElement = number;
            }
        }
        return minElement;
    }

    public static String asString(int[] elements) {

        if (elements == null) {
            return "";
        }
        String finalString = "";
        for (int element: elements) {
            finalString += element;
            if (element != elements[elements.length - 1]) {
                finalString += ", ";
            }
        }
        return finalString;
    }

    public static Character mode(String input) {
        if (input == null) {
            return null;
        }
        Character mostCommonChar = null;
        int mostComCharCounter = 0;
        for (int i = 0; i < input.length() - 1; i++) {
            char characterToLookFor = input.charAt(i);
            if (mostCommonChar == null) {
                mostCommonChar = characterToLookFor;
                mostComCharCounter = 1;
            }
            int currentCharCounter = 0;
            for (int searchableChar = 1; searchableChar < input.length() - searchableChar - i; searchableChar++) {
                if (input.charAt(searchableChar) == characterToLookFor) {
                    currentCharCounter += 1;

                }
                if (currentCharCounter > mostComCharCounter) {
                    mostCommonChar = characterToLookFor;
                }
            }

        }
        return mostCommonChar;
    }

    public static String squareDigits(String s) {
        String finalString = "";

        for (int i = 0; i < s.length(); i++) {
            char characterToCheck = s.charAt(i);

            if (Character.isDigit(characterToCheck)) {
                int numberToSq = Integer.parseInt(Character.toString(characterToCheck));
                numberToSq = numberToSq * numberToSq;
                finalString = finalString + numberToSq;
            }
            else {
                finalString += characterToCheck;
            }

        }
        return finalString;
    }

    public static int rowsInsideMatrix() {
        boolean[][] matrix = getSampleMatrix();
        printMatrix(matrix);

        int isolatedCount = 0;

        for (int i = 0; i <= 7; i++) {
            if (!matrix[0][i] && !matrix[0][i + 2] && !matrix[1][i] && !matrix[1][i + 1] && !matrix[1][i + 2]) {
                isolatedCount += 1;
            }
            if (!matrix[i][1] && !matrix[i + 1][1] && !matrix[i + 2][1] && !matrix[i][0] && !matrix[i + 2][0]) {
                isolatedCount += 1;
            }
        }
        return isolatedCount;
    }

    public static int columnsInsideMatrix() {
        boolean[][] matrix = getSampleMatrix();
        printMatrix(matrix);

        int isolatedCount = 0;
        // calculate columns
        for (int i = 0; i <= 7; i++) {
            if (!matrix[9][i] && !matrix[9][i + 2] && !matrix[8][i] && !matrix[8][i + 1] && !matrix[8][i + 2]) {
                isolatedCount += 1;
            }
            if (!matrix[i][9] && !matrix[i + 2][9] && !matrix[i][8] && !matrix[i + 1][8] && !matrix[i + 2][8]) {
                isolatedCount += 1;
            }
        }
        return isolatedCount;
    }

    public static int matrixCorners() {
        boolean[][] matrix = getSampleMatrix();
        printMatrix(matrix);

        int isolatedCount = 0;

        if (!matrix[0][1] && !matrix[1][0] && !matrix[1][1] || !matrix[1][9] && !matrix[1][8] && !matrix[0][8]) {
            isolatedCount += 1;
        }
        if (!matrix[8][9] && !matrix[8][8] && !matrix[9][8] || !matrix[8][0] && !matrix[8][1] && !matrix[9][1]) {
            isolatedCount += 1;
        }
        return isolatedCount;
    }

    public static int isolatedSquareCount() {
        boolean[][] matrix = getSampleMatrix();

        printMatrix(matrix);

        int isolatedCount = 0;

        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                if (!matrix[i][j - 1] && !matrix[i][j + 1] && !matrix[i - 1][j - 1] && !matrix[i - 1][j] && !matrix[i - 1][j + 1] && !matrix[i + 1][j + 1] && !matrix[i + 1][j - 1] && !matrix[i + 1][j]) {
                    isolatedCount += 1;
                }
            }
        }
        isolatedCount += matrixCorners() + rowsInsideMatrix() + columnsInsideMatrix();
        return isolatedCount;
    }

    private static void printMatrix(boolean[][] matrix) {
        for (boolean[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean[][] getSampleMatrix() {
        boolean[][] matrix = new boolean[10][10];

        Random r = new Random(5);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = r.nextInt(5) < 2;
            }
        }

        return matrix;
    }
}
