/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */
 class BSTNode <T> {
	public String key;
	public T data;
	public BSTNode<T> left, right;
	
	/** Creates a new instance of BSTNode */
	public BSTNode(String k, T val) {
		key = k;
		data = val;
		left = right = null;
	}
	
	
}
public class BST<T> {
    
   private  BSTNode<T> root, current;
	
	/** Creates a new instance of BST */
	public BST() {
		root = current = null;
	}
	
	public boolean empty() {
		return root == null;
	}
	
	public boolean full() {
		return false;
	}
	
	public T retrieve () {
		return current.data;
	}
        
        
        	public boolean findkey(String tkey) {
		BSTNode<T> p = root,q = root;
				
		if(empty())
			return false;
		
		while(p != null) {
			q = p;
			if(tkey.compareToIgnoreCase(p.key) == 0) {
				current = p;
				return true;
			}
			else if(tkey.compareToIgnoreCase(p.key) < 0)
				p = p.left;
			else
				p = p.right;
		}
		
		current = q;
		return false;
	}

                
                public boolean insert(String k, T val) {
		BSTNode<T> p, q = current;
		
		if(findkey(k)) {
			current = q;  // findkey() modified current
			return false; // key already in the BST
		}
		
		p = new BSTNode<T>(k, val);
		if (empty()) {
			root = current = p;
			return true;
		}
		else {
			// current is pointing to parent of the new key
			if (k.compareToIgnoreCase(current.key) < 0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}
                
                
          public void displayInOrder() {
              if (root == null) {
                 System.out.println("The tree is empty");
              } else {
                  traverseAndDisplay(root);
                }
         }
 
         private void traverseAndDisplay(BSTNode node) {
             if (node == null) return;

             traverseAndDisplay(node.left);
             System.out.println("Key: " + node.key);
            ((Word)node.data).display();
            traverseAndDisplay(node.right);
         }


    
}
