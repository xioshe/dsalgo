package org.example.base.ds.tree.binary;

import org.example.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeTraversalDfs {
    public List<Integer> preorder(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                // 出栈时是第一次访问，此时节点子节点还没有处理过
                result.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            cur = cur.right;
        }

        return result;
    }

    public List<Integer> inorder(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            // 出栈时是第二次访问，此时左子节点已经处理过了，右子节点还没处理过
            result.add(cur.val);
            cur = cur.right;
        }

        return result;
    }

    public List<Integer> postorder(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.peek();
            // 出栈时是第二次访问，此时左子节点已经处理过了，右子节点还没处理过
            if (cur.right != null && cur.right != prev) {
                cur = cur.right;
            } else {
                stack.pop();
                result.add(cur.val);
                prev = cur;
                cur = null;
            }
        }

        return result;
    }
}
