package normal_Demo;


import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class exampleTest {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {4, 1, 2, 1, 2};

        String s = "";
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        System.out.println(solution.firstChar(s));
        System.out.println(a + 2);
        sc.close();


    }

    public static String convertBrackets(String input) {
        return input.replace('[', '{').replace(']', '}');
    }

    @Test
    public void test(){
        String a = "[7,6,4,3,1]";
        System.out.println(convertBrackets(a)+";");
    }

}
