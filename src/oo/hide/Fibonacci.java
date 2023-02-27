package oo.hide;

public class Fibonacci {

    private int current = 0;
    private int next = 1;


    public int nextValue() {
        // fib(n) = fib(n - 1) + fib(n - 2)
        int result = current;

        current = next;
        next = next + result;

        return result;
    }

}
