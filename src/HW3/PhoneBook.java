package HW3;

import java.util.*;

public class PhoneBook {

    private Map<String, ArrayList<String>> book;

    public PhoneBook() {
        this.book = new HashMap<>();
    }

    public void add(String name, String ... numbers) {
        ArrayList<String> newNumbers = new ArrayList<>(Arrays.asList(numbers));
        if (book.containsKey(name)) {
            book.get(name).addAll(newNumbers);
        } else {
            book.put(name, newNumbers);
        }
    }

    public void get(String name) {
        if (book.containsKey(name)) {
            System.out.printf("%s: ", name);
            book.get(name).forEach(e -> System.out.printf("%s ", e));
            System.out.println();
        }
    }
}
