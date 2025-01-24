import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;

public class TransactionManager {
    public void borrowBook(int transactionId, int bookId, int memberId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check if the book is available
            String checkQuery = "SELECT availability FROM Books WHERE book_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt("availability") == 1) {
                // Borrow the book
                String query = "INSERT INTO Transactions (transaction_id, book_id, member_id, issue_date, due_date) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, transactionId);
                stmt.setInt(2, bookId);
                stmt.setInt(3, memberId);
                stmt.setDate(4, Date.valueOf(LocalDate.now()));
                stmt.setDate(5, Date.valueOf(LocalDate.now().plusDays(14))); // Due in 14 days
                stmt.executeUpdate();

                // Update book availability
                String updateQuery = "UPDATE Books SET availability = 0 WHERE book_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();

                System.out.println("Book borrowed successfully!");
            } else {
                System.out.println("Book is not available!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int bookId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Update return date in Transactions
            String updateTransaction = "UPDATE Transactions SET return_date = ? WHERE book_id = ? AND return_date IS NULL";
            PreparedStatement stmt = conn.prepareStatement(updateTransaction);
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, bookId);
            stmt.executeUpdate();

            // Update book availability
            String updateBook = "UPDATE Books SET availability = 1 WHERE book_id = ?";
            PreparedStatement bookStmt = conn.prepareStatement(updateBook);
            bookStmt.setInt(1, bookId);
            bookStmt.executeUpdate();

            System.out.println("Book returned successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
