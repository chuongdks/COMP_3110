import java.util.Iterator;

// Main Class to Test the Implementation
public class BstIteratorTester {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // Add student records
        tree.insert(3, "John", "Math", "Math");
        tree.insert(1, "John", "Science", "Science");
        tree.insert(4, "Joana", "History", "History");
        tree.insert(2, "Engineer", "Gaming", "Engineering");
        tree.insert(5, "Cringe", "Doktor", "Doctor");
        tree.insert(6, "John", "Jobless", "IT");

        // Preorder Traversal
        System.out.println("Preorder Traversal:");
        Iterator<StudentNode> preorderIterator = tree.getPreorderIterator();
        while (preorderIterator.hasNext()) {
            System.out.println(preorderIterator.next());
        }

        // Inorder Traversal
        System.out.println("\nInorder Traversal:");
        Iterator<StudentNode> inorderIterator = tree.getInorderIterator();
        while (inorderIterator.hasNext()) {
            System.out.println(inorderIterator.next());
        }

        // Postorder Traversal
        System.out.println("\nPostorder Traversal:");
        Iterator<StudentNode> postorderIterator = tree.getPostorderIterator();
        while (postorderIterator.hasNext()) {
            System.out.println(postorderIterator.next());
        }
    }
}
