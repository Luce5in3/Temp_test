package TreeNodeTest;

import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
    }

    public static TreeNode init() {
        // 第四层（最底层）
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);
        TreeNode n11 = new TreeNode(11);
        TreeNode n12 = new TreeNode(12);
        TreeNode n13 = new TreeNode(13);
        TreeNode n14 = new TreeNode(14);
        TreeNode n15 = new TreeNode(15);

        // 第三层
        TreeNode n4 = new TreeNode(4, n8, n9);
        TreeNode n5 = new TreeNode(5, n10, n11);
        TreeNode n6 = new TreeNode(6, n12, n13);
        TreeNode n7 = new TreeNode(7, n14, n15);

        // 第二层
        TreeNode n2 = new TreeNode(2, n4, n5);
        TreeNode n3 = new TreeNode(3, n6, n7);

        // 第一层（根节点）
        TreeNode root = new TreeNode(1, n2, n3);
        return root;
    }

    public static TreeNode buildFixedBST() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        root.right.right = new TreeNode(9);

        return root;
    }
    /**
     * 打印二叉树
     * @param node 根节点
     * @param depth 深度
     */
    private static void printTree(TreeNode node, int depth) {
        if (node == null) return;

        printTree(node.right, depth + 1);

        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println(node.val);

        printTree(node.left, depth + 1);
    }

}