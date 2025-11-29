import java.util.Scanner;

public class LibraryApp {

    public static void main(String[] args) {
        Library library = new Library();
        Notifier notifier = new ConsoleNotifier();
        Scanner scanner = new Scanner(System.in);

        // Add some sample data (optional)
        library.addBook(new Book("B1", "Clean Code", "Robert C. Martin"));
        library.addBook(new Book("B2", "Effective Java", "Joshua Bloch"));
        library.addBook(new Book("B3", "Head First Java", "Kathy Sierra"));

        library.registerMember(new Student("S1", "Yogesh"));
        library.registerMember(new Teacher("T1", "Rahul Sir"));

        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show All Books");
            System.out.println("6. Show Available Books");
            System.out.println("7. Show Issued Books");
            System.out.println("8. Show Members");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            // Handle non-int input safely
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // clear invalid input
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bid = scanner.nextLine();

                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();

                    Book newBook = new Book(bid, title, author);
                    library.addBook(newBook);
                    break;

                case 2:
                    System.out.print("Enter Member ID: ");
                    String mid = scanner.nextLine();

                    System.out.print("Enter Member Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Is this a Student or Teacher? (S/T): ");
                    String type = scanner.nextLine().trim().toUpperCase();

                    Member newMember;
                    if (type.equals("S")) {
                        newMember = new Student(mid, name);
                    } else if (type.equals("T")) {
                        newMember = new Teacher(mid, name);
                    } else {
                        System.out.println("Invalid type. Member not added.");
                        break;
                    }

                    library.registerMember(newMember);
                    break;

                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    String issueBookId = scanner.nextLine();

                    System.out.print("Enter Member ID: ");
                    String issueMemberId = scanner.nextLine();

                    library.issueBook(issueBookId, issueMemberId, notifier);
                    break;

                case 4:
                    System.out.print("Enter Book ID to return: ");
                    String returnBookId = scanner.nextLine();

                    library.returnBook(returnBookId, notifier);
                    break;

                case 5:
                    library.printAllBooks();
                    break;

                case 6:
                    library.printAvailableBooks();
                    break;

                case 7:
                    library.printIssuedBooks();
                    break;

                case 8:
                    library.printAllMembers();
                    break;

                case 0:
                    System.out.println("Exiting the application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
