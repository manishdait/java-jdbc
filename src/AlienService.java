import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlienService {
  private Connection db;

  public AlienService() throws SQLException {
    this.db = DbService.getInstance().getCon();
    init();
  }

  public boolean init() throws SQLException {
    String sql = """
      create table if not exists alien (
        id bigserial not null primary key,
        name varchar(50) not null,
        tech varchar(60) not null,
        constraint tech_ck check(tech = 'CloudComputing' or tech = 'Blockchain' or tech = 'Spring')
      )
    """;

    PreparedStatement preparedStatement = db.prepareStatement(sql);
    return preparedStatement.execute();
  }

  public List<Alien> findAll() throws SQLException {
    List<Alien> aliens = new ArrayList<>();
    
    String sql = """
      select * from alien
    """;

    PreparedStatement preparedStatement = db.prepareStatement(sql);
    ResultSet rs = preparedStatement.executeQuery();

    while (rs.next()) {
      Alien alien = new Alien();
      alien.setId(rs.getInt("id"));
      alien.setName(rs.getString("name"));
      alien.setTech(Tech.valueOf(rs.getString("tech")));

      aliens.add(alien);
    }

    return aliens;
  }

  public Alien findById(int id) throws SQLException {
    String sql = """
      select * from alien
      where id = ?
    """;

    PreparedStatement preparedStatement = db.prepareStatement(sql);
    preparedStatement.setInt(1, id);

    ResultSet rs = preparedStatement.executeQuery();
    rs.next();

    Alien alien = new Alien();
    alien.setId(rs.getInt("id"));
    alien.setName(rs.getString("name"));
    alien.setTech(Tech.valueOf(rs.getString("tech")));

    return alien;
  }

  public int save(Alien alien) throws SQLException {
    String sql = """
      insert into alien (name, tech)
      values (?, ?)
    """;

    PreparedStatement preparedStatement = db.prepareStatement(sql);
    preparedStatement.setString(1, alien.getName());
    preparedStatement.setString(2, alien.getTech().toString());

    int row = preparedStatement.executeUpdate();

    return row;
  }

  public int update(int id, Alien alien) throws SQLException {
    String sql = """
      update alien
      set name = ?, tech = ?
      where id = ?
    """;

    PreparedStatement preparedStatement = db.prepareStatement(sql);
    preparedStatement.setString(1, alien.getName());
    preparedStatement.setString(2, alien.getTech().toString());
    preparedStatement.setInt(3, id);

    int row = preparedStatement.executeUpdate();

    return row;
  }

  public int delete(int id) throws SQLException {
    String sql = """
      delete from alien
      where id = ?
    """;

    PreparedStatement preparedStatement = db.prepareStatement(sql);
    preparedStatement.setInt(1, id);

    int row = preparedStatement.executeUpdate();

    return row;
  }
}
