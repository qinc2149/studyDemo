package utils;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qinc
 * @version V1.0
 * @Description:  反转二叉树
 * @Date 2018/10/10 16:40
 */
public class Study6 {

    //反转二叉树
    public Node invertTree(Node root){
        if(root==null){
            return null;
        }
        Node temp=root.left;
        root.left=root.right;
        root.right=temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


    //删除二叉树的叶子节点
    public Node delNode(Node root){
        if(root==null){
            return root;
        }
       if (root.left==null&&root.right==null){//是叶子节点也是跟
           return  root;
       }else{//不是叶子,删除叶子节点
           delNode(root.left);
           delNode(root.right);
           root.left=null;
           root.right=null;

       }
        return root;
    }
}
class Node{
    Node left;
    Node right;
}
