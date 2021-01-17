import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    private String url = "jdbc:postgresql://localhost:5432/registration";
    private String name = "postgres";
    private String password = "2m3410lt";

    public void loadDriver(String driver){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, name, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return con;
    }

    public String insert(User user){

        loadDriver("org.postgresql.Driver");
        Connection con = getConnection();
        String result = "Data entered successfully";
        String query = "INSERT INTO person values(?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            result = "Data not entered";
            throwables.printStackTrace();
        }
        return result;
    }
}
