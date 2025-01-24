import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberManager {
    public void addMember(int memberId, String name, String email) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Members (member_id, name, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, memberId);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.executeUpdate();
            System.out.println("Member added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewMembers() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Members";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Member ID: " + rs.getInt("member_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("-----------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
