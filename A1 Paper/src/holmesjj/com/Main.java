package holmesjj.com;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] A = new int[n + 1];
        A[0] = 0;
        A[1] = 0;
        for (int i = 2; i <= n; i++) {
            A[i] = scanner.nextInt();
        }
        int[] usedA = new int[n + 1];
        Arrays.fill(usedA, 0);
        // 记录前一个size的纸所需要的数量
        int req = 1;
        boolean possible = false;
        for (int i = 2; i <= n; i++) {
            // 当前size的纸所需要的数量
            int curReq = (int) 2 * req;
            // 需要的纸已经足够
            if (A[i] >= curReq) {
                usedA[i] = curReq;
                req = 0;
                possible = true;
                break;
            }
            // 需要的纸不足够
            else {
                usedA[i] = A[i];
                // 记录当前size的纸缺的数量
                req = curReq - usedA[i];
            }
        }
        if (possible) {
            // A2的长度
            double paperLength = Math.pow(2, 3 / 4.0 * (-1));
            double[] paperLengths = new double[n + 1];
            paperLengths[0] = 0;
            paperLengths[1] = 0;
            paperLengths[2] = paperLength;
            // 长宽比
            double ratioLW = (Math.pow(2, 3 / 4.0 * (-1)) / Math.pow(2, 5 / 4.0 * (-1)));
            // 从A3开始计算每个size的长度并保存
            for (int i = 3; i <= n; i++) {
                paperLength = paperLength / 2 * ratioLW;
                paperLengths[i] = paperLength;
            }
            double result = 0;
            // 从An -> A2倒着计算
            for (int i = n; i >= 2; i--) {
                // 当前size的两张纸合并成前一个size的纸，同时也是当前size的纸需要的胶带的数量
                int preA = usedA[i] / 2;
                result = result + preA * paperLengths[i];
                usedA[i - 1] = usedA[i - 1] + preA;
            }
            System.out.println(result);
        } else {
            System.out.println("impossible");
        }
    }
}
