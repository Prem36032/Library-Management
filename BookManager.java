import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookManager {
    public void addBook(int bookId, String title, String author, String publisher) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Books (book_id, title, author, publisher, availability) VALUES (?, ?, ?, ?, 1)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, bookId);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setString(4, publisher);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBooks() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Books";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Book ID: " + rs.getInt("book_id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Publisher: " + rs.getString("publisher"));
                System.out.println("Availability: " + (rs.getInt("availability") == 1 ? "Available" : "Not Available"));
                System.out.println("-----------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
