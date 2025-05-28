package Dynamic_proxies;

public class Main {
    public static void main(String[] args) throws Exception {
        int i= 0 ;
        //i = i++ ;
        i = ++i;
        System.out.println(i);
//        final List<String> list1 = new ArrayList<>();
//
//        // 使用反射获取 Unsafe 实例
//        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//        theUnsafe.setAccessible(true);
//        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
//
//        // 获取对象的内存地址
//        long address = unsafe.allocateMemory(8);
//        System.out.println("List 内存地址: " + address);
    }
}
