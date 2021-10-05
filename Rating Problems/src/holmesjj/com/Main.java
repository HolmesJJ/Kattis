package holmesjj.com;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        String[] line1Arr = line1.split(" ");
        int n = Integer.parseInt(line1Arr[0]);
        int k = Integer.parseInt(line1Arr[1]);
        int sum = 0;
        for (int i = 0; i < k; i++) {
            int rating = Integer.parseInt(scanner.nextLine());
            sum = sum + rating;
        }
        double min = 1.0 * (sum - 3 * (n - k)) / n;
        double max = 1.0 * (sum + 3 * (n - k)) / n;
        System.out.println(min + " " + max);
    }
}
