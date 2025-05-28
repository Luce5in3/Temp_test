package algorithm_Demo;

import org.w3c.dom.ls.LSInput;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
       return mergeKLists(lists, 0, lists.length );
    }
    private ListNode mergeKLists(ListNode[] lists, int i, int j) {
        int m = j - i;
        if (m == 0) {
            return null; // 注意输入的 lists 可能是空的
        }
        if (m == 1) {
            return lists[i]; // 无需合并，直接返回
        }
        ListNode left = mergeKLists(lists, i, i + m / 2); // 合并左半部分
        ListNode right = mergeKLists(lists, i + m / 2, j); // 合并右半部分
        return mergeTwoLists(left, right); // 最后把左半和右半合并
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while(l1 != null && l2 != null){
            if(l1.val < l2.val) {
                curr.next = l1;
                l1= l1.next;
            }else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
