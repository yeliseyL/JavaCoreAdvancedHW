package HW3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> arr = new ArrayList<>(Arrays.asList("cat", "dog", "cat",
                "tiger", "cougar", "giraffe", "cat", "bird", "dog", "bird"));

        countElements(arr);

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Ivanov", "1111111");
        phoneBook.add("Petrov", "2222222", "3333333");
        phoneBook.add("Sidorov", "4444444", "5555555");
        phoneBook.add("Ivanov", "6666666", "7777777");
        phoneBook.get("Ivanov");
        phoneBook.get("Petrov");
        phoneBook.get("Sidorov");

    }

    public static void countElements(List<String> list) {
        Map<String, Long> count = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        count.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
