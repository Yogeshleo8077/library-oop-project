public class Student extends Member {
    public Student(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public int getMaxBooksAllowed() {
        return 3; // Students can issue max 3 books
    }

    @Override
    public String toString() {
        return "[Student] ID: " + memberId + ", Name: " + name;
    }

}