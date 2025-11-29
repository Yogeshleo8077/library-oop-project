public class Teacher extends Member {
    public Teacher(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public int getMaxBooksAllowed() {
        return 5; // teachers can issue max 5 books
    }

    @Override
    public String toString() {
        return "[Teacher] ID: " + memberId + ", Name: " + name;
    }
}
