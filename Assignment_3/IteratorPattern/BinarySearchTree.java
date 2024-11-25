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
        root = insertRecursive(root, new StudentNode(studentId, firstName, lastName, department));
    }

    /**
     * 
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
        return new PreorderIterator(root);
    }

    public Iterator<StudentNode> getInorderIterator() {
        return new InorderIterator(root);
    }

    public Iterator<StudentNode> getPostorderIterator() {
        return new PostorderIterator(root);
    }
}

// Preorder Iterator
class PreorderIterator implements Iterator<StudentNode> {
    private Stack<StudentNode> stack = new Stack<>();

    public PreorderIterator(StudentNode root) {
        if (root != null) stack.push(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public StudentNode next() {
        StudentNode current = stack.pop();
        if (current.right != null) stack.push(current.right);
        if (current.left != null) stack.push(current.left);
        return current;
    }
}

// Inorder Iterator
class InorderIterator implements Iterator<StudentNode> {
    private Stack<StudentNode> stack = new Stack<>();
    private StudentNode current;

    public InorderIterator(StudentNode root) {
        current = root;
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty() || current != null;
    }

    @Override
    public StudentNode next() {
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        current = stack.pop();
        StudentNode node = current;
        current = current.right;
        return node;
    }
}

// Postorder Iterator
class PostorderIterator implements Iterator<StudentNode> {
    private Stack<StudentNode> stack1 = new Stack<>();
    private Stack<StudentNode> stack2 = new Stack<>();

    public PostorderIterator(StudentNode root) {
        if (root != null) stack1.push(root);
        while (!stack1.isEmpty()) {
            StudentNode current = stack1.pop();
            stack2.push(current);
            if (current.left != null) stack1.push(current.left);
            if (current.right != null) stack1.push(current.right);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack2.isEmpty();
    }

    @Override
    public StudentNode next() {
        return stack2.pop();
    }
}