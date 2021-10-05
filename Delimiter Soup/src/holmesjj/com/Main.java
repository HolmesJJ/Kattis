package holmesjj.com;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        char[] brackets = scanner.nextLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        boolean isPrint = false;
        for (int i = 0; i < length; i++) {
            if (brackets[i] == ' ') {
                continue;
            }
            if (!stack.empty() && ((stack.peek() == '(' && brackets[i] == ')')
                || (stack.peek() == '[' && brackets[i] == ']')
                || (stack.peek() == '{' && brackets[i] == '}'))) {
                stack.pop();
                continue;
            }
            stack.push(brackets[i]);
            if (brackets[i] == ')' || brackets[i] == ']' || brackets[i] == '}') {
                System.out.println(brackets[i] + " " + i);
                isPrint = true;
                break;
            }
        }
        if (!isPrint) {
            System.out.println("ok so far");
        }
    }
}
