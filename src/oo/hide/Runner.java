package oo.hide;

import oo.hide.Timer;
import org.junit.Test;

public class Runner {

    @Test
    public void timerExample() {

        Timer timer = new Timer();

        int sum = 0;
        for (int i = 0; i < 1E8; i++) {
            sum++;
        }

        System.out.println(sum);
        System.out.println(timer.getPassedTime());
    }



}
