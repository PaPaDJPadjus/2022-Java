package generics.list;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> {

    private List<T> elements = new ArrayList<>();

    public void add(T element) {
        elements.add(element);
    }

    public void addAll(List elements) {
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
