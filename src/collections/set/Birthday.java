package collections.set;

import org.junit.Test;

import java.util.*;


public class Birthday {

    @Test
    public void runCode() {

        Random r = new Random();

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < 365; i++) {
            int randomDayOfYear = r.nextInt(365);

            if (set.contains(randomDayOfYear)) {
                System.out.println("found" + i);
                break;
            } else {
                set.add(randomDayOfYear);
            }
        }
    }
}
