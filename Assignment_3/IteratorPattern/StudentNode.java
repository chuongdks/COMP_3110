/**
 * StudentNode is an Info expert on student information, also contain left and right node
 */
class StudentNode {
    String firstName;
    String lastName;
    String department;
    int studentId;
    StudentNode left, right;

    public StudentNode(int studentId, String firstName, String lastName, String department) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.left = this.right = null;
    }

    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + firstName + " " + lastName + ", Dept: " + department;
    }
}