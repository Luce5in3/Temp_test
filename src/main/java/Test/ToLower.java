package Test;

import java.util.Scanner;

public class ToLower {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入字符串（输入 exit 退出）：");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("程序结束。");
                break;
            }
            System.out.println("小写结果：" +"param_"+ input.toLowerCase());
        }

        scanner.close();
    }
}

