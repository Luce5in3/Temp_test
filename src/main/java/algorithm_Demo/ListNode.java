package algorithm_Demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        next = null;
    }
}
