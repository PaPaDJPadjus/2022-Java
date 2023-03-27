package generics.stack;

public class Stack {

    private Integer[] elements;
    private Integer size = 0;

    public Stack() {
        elements = new Integer[100];
    }

    public void push(Integer element) {
        elements[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public Integer pop() {
        if (size == 0) {
            throw new IllegalStateException("stack is empty");
        }

        return elements[--size];
    }

}
