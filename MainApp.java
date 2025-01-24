import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        MemberManager memberManager = new MemberManager();
        TransactionManager transactionManager = new TransactionManager();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add Member");
            System.out.println("4. View Members");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Publisher: ");
                    String publisher = scanner.nextLine();
                    bookManager.addBook(bookId, title, author, publisher);
                    break;

                case 2:
                    bookManager.viewBooks();
                    break;

                case 3:
                    System.out.print("Enter Member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    memberManager.addMember(memberId, name, email);
                    break;

                case 4:
                    memberManager.viewMembers();
                    break;

                case 5:
                    System.out.print("Enter Transaction ID: ");
                    int transactionId = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    bookId = scanner.nextInt();
                    System.out.print("Enter Member ID: ");
                    memberId = scanner.nextInt();
                    transactionManager.borrowBook(transactionId, bookId, memberId);
                    break;

                case 6:
                    System.out.print("Enter Book ID to Return: ");
                    bookId = scanner.nextInt();
                    transactionManager.returnBook(bookId);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
