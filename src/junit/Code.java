package junit;


import java.util.Arrays;

public class Code {

    public static void main(String[] args) {
        System.out.println(getCharacterCount("abbcccdddbbddb", 'b'));
    }

    public static boolean isSpecial(int candidate) {
        return candidate % 11 == 0 || candidate % 11 == 1 || candidate % 11 == 2 || candidate % 11 == 3;
    }

    public static int longestStreak(String inputString) {
        if (inputString.equals("")) {
            return 0;
        }
        if (inputString.length() == 1) {
            return 1;
        }

        int record = 0;
        int streakCounter = 0;
        for (int i = 0; i <= inputString.length() - 1; i++) {
            char charFromString = inputString.charAt(i);
            for (int j = i; j < inputString.length(); j++) {
                char charsAfterSearchableChar = inputString.charAt(j);
                if (charFromString == charsAfterSearchableChar) {
                    streakCounter += 1;
                }
                else {
                    break;
                }
            }
            if (streakCounter > record) {
                record = streakCounter;
            }
            streakCounter = 0;
        }
        return record;
    }

    public static Character mode(String input) {
        if (input == null || input.equals("")) {
            return null;
        }
        int mostComCharCounter = 0;
        Character mostCommonChar = null;
        for (int i = 0; i <= input.length() - 1; i++) {
            int newCharAmount = getCharacterCount(input, input.charAt(i));
            if (newCharAmount > mostComCharCounter) {
                mostCommonChar = input.charAt(i);
                mostComCharCounter = newCharAmount;
            }
        }
        return mostCommonChar;
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter) {

        int currentCharCounter = 0;
        for (int searchableChar = 0; searchableChar < allCharacters.length(); searchableChar++) {

            if (allCharacters.charAt(searchableChar) == targetCharacter) {
                currentCharCounter += 1;
            }
        }
        return currentCharCounter;
    }

    public static int integerInArrayCount(int integer, int[] integersArray) {
        int counter = 0;
        for (int el : integersArray) {
            if (integer == el) {
                counter += 1;
            }
        }
        return counter;
    }

    public static int[] addNewSlotWithElToArray(int element, int[] integers) {
        int[] outputArray = new int[integers.length + 1];
        int index = 0;
        for (int el : integers) {
            outputArray[index] = el;
            index += 1;
        }
        outputArray[index] = element;
        return outputArray;
    }

    public static int[] removeDuplicates(int[] integers) {

        int[] integersWithoutDuplicates = new int[0];
            for (int el : integers) {

                if (integerInArrayCount(el, integers) == 1) {
                    integersWithoutDuplicates = addNewSlotWithElToArray(el, integersWithoutDuplicates);
                }
                if (integerInArrayCount(el, integers) > 1 && integerInArrayCount(el, integersWithoutDuplicates) == 0) {
                    integersWithoutDuplicates = addNewSlotWithElToArray(el, integersWithoutDuplicates);
                }
            }
        return integersWithoutDuplicates;

    }

    public static int sumIgnoringDuplicates(int[] integers) {
        int sumOfElements = 0;
        int[] arrayWithoutDuplicates = removeDuplicates(integers);
        for (int value : arrayWithoutDuplicates) {
            sumOfElements += value;
        }
        return sumOfElements;
    }

}
