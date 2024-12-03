import java.util.Iterator;
import java.util.Stack;

// Binary Search Tree Class
class BinarySearchTree {
    private StudentNode root; // the root StudentNode of the Binary Search Tree

    /**
     * Insert a new student record
     * @param studentId
     * @param firstName
     * @param lastName
     * @param department
     */
    public void insert(int studentId, String firstName, String lastName, String department) 
    {
        root = insertRecursive(root, new StudentNode(studentId, firstName, lastName, department)); // Always return the root of the node
    }

    /**
     * Recursively insert new Student record based on the BST logic
     * @param current
     * @param newNode
     * @return
     */
    private StudentNode insertRecursive(StudentNode current, StudentNode newNode) 
    {
        // If no root node in that branch is created, return newNode
        if (current == null) return newNode;

        // Insert new node by cmp studentID. Insert order based on BST
        if (newNode.studentId < current.studentId)
        {
            current.left = insertRecursive(current.left, newNode);
        }
        else if (newNode.studentId > current.studentId)
        {
            current.right = insertRecursive(current.right, newNode);
        }
            
        return current;
    }

    /**
     * Get iterators for preorder, inorder, and postorder traversal
     * @return Iterators for Preorder, Inorder and Postorder traversal
     */
    public Iterator<StudentNode> getPreorderIterator() {
        Iterator<StudentNode> preorderIterator = new PreorderIterator(root);
        return preorderIterator;
    }

    public Iterator<StudentNode> getInorderIterator() {
        Iterator<StudentNode> InorderIterator = new InorderIterator(root);
        return InorderIterator;
    }

    public Iterator<StudentNode> getPostorderIterator() {
        Iterator<StudentNode> PostorderIterator = new PostorderIterator(root);
        return PostorderIterator;
    }
}

/**
 * Preorder Iterator (Root -> Left -> Right). Hints: https://stackoverflow.com/questions/71543956/binary-search-tree-using-the-iterator-interface
 */
class PreorderIterator implements Iterator<StudentNode> {
    private Stack<StudentNode> stack = new Stack<>();

    /**
     * 
     * @param root
     */
    public PreorderIterator(StudentNode root) {
        if (root != null) stack.push(root);
    }

    /**
     * 
     */
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * 
     */
    @Override
    public StudentNode next() {
        StudentNode current = stack.pop();
        if (current.right != null) stack.push(current.right);
        if (current.left != null) stack.push(current.left);
        return current;
    }
}

/**
 * Inorder Iterator (Left -> Root -> Right). Hints: https://stackoverflow.com/questions/65186904/binary-search-tree-inorder-iterator-next-method
 */
class InorderIterator implements Iterator<StudentNode> {
    private Stack<StudentNode> stack = new Stack<>();
    private StudentNode current;

    /**
     * 
     * @param root
     */
    public InorderIterator(StudentNode root) {
        current = root;
    }

    /**
     * 
     */
    @Override
    public boolean hasNext() 
    {
        return !stack.isEmpty() || current != null;
    }

    /**
     * 
     */
    @Override
    public StudentNode next() 
    {
        // Push left node to the stack til no more Left child
        while (current != null)
        {
            stack.push(current);
            current = current.left;
        }
        // If current is null, pop into a node to return it, update current to right child
        current = stack.pop();
        StudentNode node = current;
        current = current.right;
        return node;
    }
}

/**
 * Postorder Iterator (Left -> Right -> Root). Tips: https://www.geeksforgeeks.org/iterative-postorder-traversal/
 */
class PostorderIterator implements Iterator<StudentNode> {
    private Stack<StudentNode> stack1 = new Stack<>();
    private Stack<StudentNode> stack2 = new Stack<>();

    /**
     * Run while Stack 1 is not empty
     * @param root
     */
    public PostorderIterator(StudentNode root) {
        if (root != null) stack1.push(root);
        while (!stack1.isEmpty()) 
        {
            StudentNode current = stack1.pop();
            stack2.push(current);
            if (current.left != null) stack1.push(current.left);
            if (current.right != null) stack1.push(current.right);
        }
    }

    /**
     * Check empty by checking Stack 2
     */
    @Override
    public boolean hasNext() {
        return !stack2.isEmpty();
    }

    /**
     * Use Stack 2 to display Postorder
     */
    @Override
    public StudentNode next() {
        return stack2.pop();
    }
}