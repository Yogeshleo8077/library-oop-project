import java.util.*;

public class Library {
    private List<Book> books;
    private List<Member> members;

    // bookId -> memberId
    private Map<String, String> issuedBooks;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.issuedBooks = new HashMap<>();
    }

    // Add a new book to library
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    // Register new member
    public void registerMember(Member member) {
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    // Find book by ID
    public Book findBookById(String id) {
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(id)) {
                return book;
            }
        }
        return null;
    }

    // Find member by Id
    public Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                return member;
            }
        }

        return null;
    }

    // Count books already issued to this member
    private int getIssuedCountForMember(String memberId) {
        int count = 0;
        for (String mid : issuedBooks.values()) {
            if (mid.equalsIgnoreCase(memberId)) {
                count++;
            }
        }
        return count;
    }

    // Issue a book
    public void issueBook(String bookId, String memberId, Notifier notifier) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found with ID: " + bookId);
            return;
        }

        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("Member not found with ID: " + memberId);
            return;
        }

        if (book.isIssued()) {
            System.out.println("Book is already issued to someone else.");
            return;
        }

        int alreadyIssued = getIssuedCountForMember(memberId);
        if (alreadyIssued >= member.getMaxBooksAllowed()) {
            System.out.println("Member has already reached max limit of issued books.");
            return;
        }

        book.issue();
        issuedBooks.put(bookId, memberId);
        System.out.println("Book issued successfully.");
        notifier.send("Book '" + book.getTitle() + "' issued to " + member.getName());
    }

    // Return a book
    public void returnBook(String bookId, Notifier notifier) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found with ID: " + bookId);
            return;
        }

        if (!book.isIssued()) {
            System.out.println("This book is not currently issued.");
            return;
        }

        String memberId = issuedBooks.get(bookId);
        book.returnBook();
        issuedBooks.remove(bookId);
        System.out.println("Book returned successfully.");
        notifier.send("Book '" + book.getTitle() + "' returned by member ID: " + memberId);
    }

    // Show all books
    public void printAllBooks() {
        System.out.println("---- All Books ----");
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Show only available books
    public void printAvailableBooks() {
        System.out.println("---- Available Books ----");
        boolean found = false;
        for (Book book : books) {
            if (!book.isIssued()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available books.");
        }
    }

    // Show issued books with member info
    public void printIssuedBooks() {
        System.out.println("---- Issued Books ----");
        if (issuedBooks.isEmpty()) {
            System.out.println("No books are currently issued.");
            return;
        }

        for (Map.Entry<String, String> entry : issuedBooks.entrySet()) {
            String bookId = entry.getKey();
            String memberId = entry.getValue();
            Book book = findBookById(bookId);
            Member member = findMemberById(memberId);

            System.out.println("Book: " + book.getTitle() +
                    " (ID: " + book.getId() + ")" +
                    " => Issued to: " + member.getName() +
                    " (Member ID: " + member.getMemberId() + ")");
        }
    }

    // Show all members
    public void printAllMembers() {
        System.out.println("---- Members ----");
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        for (Member member : members) {
            System.out.println(member);
        }
    }
}
