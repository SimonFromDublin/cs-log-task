import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Database {

    public void createTable() {

        int result = 0;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            String url = "jdbc:hsqldb:hsql://localhost/simondb";
            Connection conn = DriverManager.getConnection(url, "SA", "");
            Statement stmt = conn.createStatement();
            result = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS log_table (id VARCHAR(15) NOT NULL, duration INT NOT NULL, type VARCHAR(50), VARCHAR(50), flag BOOLEAN);");
            conn.close();
            System.out.println("Table created successfully");
        } catch (Exception e) {
            System.out.println("Cannot create table! " + e.getMessage());
        }
    }


    public void insert (String id, int duration, String type, String host, boolean flag) {

        System.out.println(id+" "+duration+" "+type+" "+host+" "+flag);

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            String url = "jdbc:hsqldb:hsql://localhost/simondb";
            Connection conn = DriverManager.getConnection(url,"SA","");
            Statement st = conn.createStatement();
            String query = " INSERT INTO log_table(id, duration, status, flag) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, id);
            preparedStmt.setInt (2, duration);
            preparedStmt.setString   (3, type);
            preparedStmt.setString   (4, host);
            preparedStmt.setBoolean(5, flag);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
            System.out.println("Cannot insert a raw! " + e.getMessage());
        }
    }
}
