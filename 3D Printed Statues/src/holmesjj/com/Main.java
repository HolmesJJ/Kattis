package holmesjj.com;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int statues = Integer.parseInt(scanner.nextLine());
        int printerPrinters = (int) Math.ceil(Math.log(statues) / Math.log(2));
        System.out.println(printerPrinters + 1);
    }
}
