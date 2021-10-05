package holmesjj.com;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line1 = scanner.nextLine().split(" ");
        int N = Integer.parseInt(line1[0]);
        int t = Integer.parseInt(line1[1]);
        String[] line2 = scanner.nextLine().split(" ");
        Integer[] inputs = Stream.of(line2).map(Integer::valueOf).toArray(Integer[]::new);
        if (t == 1) {
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < N; i++) {
                hashMap.put(7777 - inputs[i], inputs[i]);
            }
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                if (hashMap.get(inputs[i]) != null && hashMap.get(inputs[i]).intValue() != inputs[i].intValue()) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } else if (t == 2) {
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < N; i++) {
                hashMap.put(inputs[i], inputs[i]);
            }
            if (hashMap.size() == N) {
                System.out.println("Unique");
            } else {
                System.out.println("Contains duplicate");
            }
        } else if (t == 3) {
            Integer[] outputs = new Integer[N];
            Arrays.fill(outputs, 0);
            for (int i = 0; i < N; i++) {
                outputs[inputs[i]] = outputs[inputs[i]] + 1;
            }
            int result = -1;
            for (int i = 0; i < N; i++) {
                if (outputs[i] > N / 2) {
                    result = i;
                    break;
                }
            }
            System.out.println("" + result);
        } else if (t == 4) {
            Arrays.sort(inputs);
            if (N % 2 != 0) {
                System.out.println("" + inputs[N / 2]);
            } else {
                System.out.println("" + inputs[N / 2 - 1] + " " + inputs[N / 2]);
            }
        }
        else if (t == 5) {
            String outputs = Arrays.stream(inputs).filter(input -> input >= 100 && input <= 999)
                .sorted().map(Object::toString).collect(Collectors.joining(" "));
            System.out.println(outputs);
        }
    }
}