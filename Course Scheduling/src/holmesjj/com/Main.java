package holmesjj.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        HashMap<String, String> requests = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] contents = line.split(" ");
            requests.put(line, contents[2]);
        }
        HashMap<String, Integer> outputs = new HashMap<>();
        requests.forEach((key, value) -> outputs.put(value, outputs.get(value) != null ? outputs.get(value) + 1 : 1));
        List<Map.Entry<String,Integer>> list = new ArrayList<>(outputs.entrySet());
        list.sort(Map.Entry.comparingByKey());
        list.forEach(item -> System.out.println(item.getKey() + " " + item.getValue()));
    }
}
