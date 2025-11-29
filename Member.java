public abstract class Member {
    protected String memberId;
    protected String name;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    // Each type of member can have different max books
    public abstract int getMaxBooksAllowed();

    @Override
    public String toString() {
        return "[Member] ID: " + memberId + ", Name: " + name;
    }
}