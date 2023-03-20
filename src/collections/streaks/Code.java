package collections.streaks;

import java.util.*;

public class Code {

    public static List<List<String>> getStreakList(String input) {
        LinkedList<List<String>> streaks = new LinkedList<>();

        for (char character : input.toCharArray()) {

            String currentChar = String.valueOf(character);

            if (streaks.size() == 0) {
                streaks.add(new LinkedList<>(Arrays.asList(currentChar)));
            } else if (streaks.getLast().contains(currentChar)){
                streaks.getLast().add(currentChar);
            } else {
                streaks.add(new LinkedList<>(Arrays.asList(currentChar)));
            }
        }
        return streaks;
    }
}
