package algorithm_Demo;

public class Test {
    public static void main(String[] args) {

    }


    // 可选：打印整个链表（方便调试）
    public static void print(Node head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    // 可选：构建链表工具方法
    public static Node build(int[] values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }
}
