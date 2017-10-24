/*
 * BSTree.java
 * @author Rakesh Raju
 * 10/23/17
 * A class that is a representation of a Binary Search Tree
 * 
 */


public class BSTree {

	//various data fields, starting node, preOrdered string, and inOrdered string
	Node root;
	String preOrder = "";
	String inOrder = "";

	public BSTree() {
		this.root = null;
	}

	//insert function, inserts a value at the last appropriate available node
	public void insert(String string) {
		Node temp = new Node(string);
	
		//if the tree is empty, immediately inserts into first node
		if (root == null) {
			root = temp;
			return;
		}

		Node current = root;
		Node parent = null;

		//checks whether the string is supposed to be on the left or ride side of the node/tree, once inserted, exits the loop/method
		while (true) {
			parent = current;
			if (string.compareTo(current.data) < 0) {
				current = current.left;
				if (current == null) {
					parent.left = temp;
					return;
				}
			}
			else {
				current = current.right;
				if (current == null) {
					parent.right = temp;
					return;
				}
			}
		}

	}

	//find function that takes in a searched value, and traverses the tree to find whether it exists in the tree (returns false if missing value)
	public boolean find(String string) {
		Node current = root;
		while (current != null) {
			if (current.data.equals(string)) {
				return true;
			} else if (current.data.compareTo(string) > 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return false;
	}

	//delete function that takes in a value to be deleted, traverses through the tree and deletes upon certain conditions
	public void delete(String string) {
		Node parent = root;
		Node current = root;
		boolean left = false;

		//traverses tree to find the the value in the tree, then sets the current node to that one
		while (!current.data.equals(string)) {
			parent = current;
			if (current.data.compareTo(string) > 0) {
				left = true;
				current = current.left;
			} else {
				left = false;
				current = current.right;
			}

			if (current == null) {
				return;
			}
		}
		//cases for node deletion
		
		//if node has no children, deletes it immediately
		if (current.left == null && current.right == null) {
			if (current == root) {
				root = null;
			}
			if (left) {
				parent.left = null;
			} else {
				parent.right = null;
			}

		}

		//if node has only one child node
		else if (current.right == null) {
			if (current == root) {
				root = current.left;
			} else if (left) {
				parent.left = current.left;
			} else {
				parent.right = current.right;
			}
		}

		else if (current.left == null) {
			if (current == root) {
				root = current.right;
			} else if (left) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		}

		//last possible node in the tree
		else if (current.left != null && current.right != null) {
			Node replace = getReplace(current);

			if (current == root) {
				root = replace;
			} else if (left) {
				parent.left = replace;
			} else {
				parent.right = replace;
			}
			replace.left = current.left;
		}

	}

	//finds the right child, and moves it to the left one as a replacement for the deleted node
	public Node getReplace(Node replace) {
		Node temp = null;
		Node tempParent = null;
		Node current = replace.right;

		while (current != null) {
			tempParent = temp;
			temp = current;
			current = current.left;
		}

		if (temp != replace.right) {
			tempParent.left = temp.right;
			temp.right = replace.right;
		}

		return temp;
	}

	//returns the tree as a sorted, space-separated string
	public String toStringInOrder() {
		toStringInOrder(root);
		//minor trimming for the extra space
		inOrder = inOrder.substring(0, inOrder.length() - 1);
		return inOrder;

	}

	//recursive helper method that populates the string inOrder with the sorted form of the tree
	public void toStringInOrder(Node node) {
		if (node == null) {
			return;
		}
		toStringInOrder(node.left);
		//condition to make sure it doesn't duplicate
		if (!inOrder.contains(node.data)) {
			inOrder += node.data + " ";
		}
		toStringInOrder(node.right);
		

	}

	//returns the tree as it would appear left to right
	public String toStringPreOrder() {
		toStringPreOrder(root);
		//minor trimming for the extra space
		preOrder = preOrder.substring(0, preOrder.length() - 1);
		return preOrder;
	}

	//recursive helper method that populates the string preOrder with the regularly displayed form of the tree
	public void toStringPreOrder(Node node) {
		if (node == null) {
			return;
		}
		//condition to make sure it doesn't duplicate
		if (!preOrder.contains(node.data)) {
			preOrder += node.data + " ";
		}
		toStringPreOrder(node.left);
		toStringPreOrder(node.right);
	}

}
