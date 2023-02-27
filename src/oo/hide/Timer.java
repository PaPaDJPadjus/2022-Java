package oo.hide;

public class Timer {
    private long start = System.currentTimeMillis();

    public String getPassedTime() {
        double passedTime = System.currentTimeMillis() - start;
        return String.format("%s sek", passedTime / 1000);
    }
}
