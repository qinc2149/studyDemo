package utils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @author qinc
 * @version V1.0
 * @Description:  反转二叉树
 *
 * 5,你自己实现一个本地缓存，淘汰最久未使用，你怎么设计
6,用栈实现计算器
7,剔除二叉树的叶子节点
8,反转二叉树左右节点
 *
 *
 * @Date 2018/10/10 16:40
 */
public class Study6 {

    public static void main(String args[]){

    }



    // 建立二叉树
    public TreNode createTree(TreNode root) {
        root = new TreNode(1);
        root.left = createTree(root.left);
        root.right = createTree(root.right);
        return root;
    }

    //反转二叉树（递归）
    public void Mirror(TreNode root){
        if(root==null){
            return;
        }
        if(root.left==null&&root.right==null){
            return;
        }
        TreNode temp= root.left;
        root.left=root.right;
        root.right=temp;
        Mirror(root.left);
        Mirror(root.right);
    }



    //反转二叉树（非递归），其实是使用栈的原理
    public void MirrorNotRecursive(TreNode root) {
        LinkedList<TreNode> nodeTack= new LinkedList<>();
        if(root==null){
            return;
        }
        nodeTack.add(root);//将根节点放入list中
        while(nodeTack.size()!=0){//将节点取出
            TreNode node=nodeTack.removeFirst();
            TreNode temp= root.left;
            root.left=root.right;
            root.right=temp;
            if(root.right!=null){
                nodeTack.add(root.right);
            }
            if(root.left!=null){
                nodeTack.add(root.left);
            }

        }

    }


    //平层遍历二叉树
    public void levelTraverse(TreNode root){
        LinkedList<TreNode> nodeTack= new LinkedList<>();
        if(root==null){
            return;
        }
        nodeTack.add(root);//将根节点放入list中
        while(nodeTack.size()!=0){//将节点取出
            TreNode node=nodeTack.removeFirst();
            System.out.println(node.val);
            if(root.right!=null){
                nodeTack.add(root.right);
            }
            if(root.left!=null){
                nodeTack.add(root.left);
            }

        }
    }



}
class TreNode {
    int val = 0;
    TreNode left = null;
    TreNode right = null;

    public TreNode(int val) {
        this.val = val;

    }
}
