package Test;

public class JStest {
    public static void main(String[] args) {

        int testValue = 1000;
        outerLoop:  // 标签名
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 3) {
                    break outerLoop; // 跳出 outerLoop 外层循环
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }

        System.out.printf("hello is my way %d",100);
    }
}
