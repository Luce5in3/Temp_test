package stringTest;

public class stringMain {
    public static void main(String[] args) {
        String a = new String("a");
        String b = new String("a");
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
    }
}
