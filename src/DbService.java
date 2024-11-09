import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbService {
  final String URL = "jdbc:postgresql://localhost:5432/mydb";
  final String USERNAME = "postgres";
  final String PASSWORD = "password";

  private final Connection con;
  private static DbService instance;

  private DbService() throws SQLException {
    this.con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
  }

  public Connection getCon() {
    return con;
  }

  public static DbService getInstance() throws SQLException {
    if (instance != null) {
      return instance;
    }
    
    DbService.instance = new DbService();
    return instance;
  }
}
