package TreeNodeTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    //树的最大直径（节点之间最远路径）
    public static int maxDiameter;
    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

//-------------------------------------------------前中后序遍历----------------------------------------------------------\\
    /**
     * 前中后序遍历
     * @param root 根节点
     * @param list
     */
    public void pre_orderTraversal(TreeNode root, List<Integer> list){
       if(root == null) return;
       list.add(root.val);
       pre_orderTraversal(root.left, list);
       pre_orderTraversal(root.right, list);
    }
    public void middle_orderTraversal(TreeNode root, List<Integer> list){
        if(root == null) return ;
        middle_orderTraversal(root.left, list);
        list.add(root.val);
        middle_orderTraversal(root.right, list);
    }
    public void post_orderTraversal(TreeNode root, List<Integer> list){
        if (root == null) return;
        post_orderTraversal(root.left, list);
        post_orderTraversal(root.right, list);
        list.add(root.val);
    }
//-----------------------------------------------------二叉树的层序遍历---------------------------------------------------------\\

    /**
     * 层序遍历（使用队列实现）
     * @param root
     * @return
     */
    public  List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            res.add(list);
        }
        return res;
    }
//-----------------------------------------------------最大深度---------------------------------------------------------\\

    /**
     * 最大深度（递归）
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }
//-----------------------------------------------------翻转二叉树---------------------------------------------------------\\

    /**
     * 翻转二叉树（递归）
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        // 递归翻转左右子树
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // 交换左右子树
        root.left = right;
        root.right = left;

        return root;
    }
//-----------------------------------------------------对称二叉树---------------------------------------------------------\\

    /**
     * 调用函数判断左右节点是否对称
     * @param root 根节点
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    /**
     * 判断左右是否对称
     * @param left  左节点
     * @param right 右节点
     * @return
     */
    private boolean isMirror(TreeNode  left, TreeNode right) {
        if ( left == null && right == null) return true;
        if ( left == null || right == null) return false;
        return ( left.val == right.val)
                && isMirror( left.left, right.right)
                && isMirror( left.right, right.left);
    }
//-----------------------------------------------------最大直径---------------------------------------------------------\\

    /**
     * 最大的直径也就是这个左子树和右子树深度的和(一个节点到另一个节点的最大值)
     * @param root 根节点
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        maxDepth(root);
        return maxDiameter;
    }
//-----------------------------------------------------检验是否是二叉搜索树-----------------------------------------------\\

    public static boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    /**
     * 递归遍历
     * @param node  根节点
     * @param min   最小值
     * @param max   最大值
     * @return
     */
    private static boolean isValid(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }
//-----------------------------------------------------升序数组转化为二叉搜索树---------------------------------------------------------\\

    public TreeNode sortedArrayToBST(int[] nums) {
        return  build(nums , 0 ,nums.length-1);
    }

    /**
     * 升序数组转化为二叉搜索树（二分思想）
     * @param nums  数组
     * @param left  左标记
     * @param right 右标记
     * @return
     */
    private static TreeNode build(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2; // 避免溢出
        TreeNode node = new TreeNode(nums[mid]);
        node.left = build(nums, left, mid - 1);
        node.right = build(nums, mid + 1, right);
        return node;
    }
}