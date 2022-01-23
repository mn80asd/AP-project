//package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB {

    public DB() throws SQLException, ClassNotFoundException {
    }

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        createTable();
    }
    public static void createTable() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement ("CREATE TABLE IF NOT EXISTS tablename(id int NOT NULL AUTO_INCREMENT,first varchar(255),last varchar(255),PRIMARY KEY(id))");
            create.executeUpdate();
        }catch(Exception e){System.out.println(e);}
        finally{
            System.out.println("Function Complete.");
        };
    }
    public static Connection getConnection() throws Exception{}
        try{
            String driver ="com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://24.196.52.166:3306/testab";
            String username = "hey";
            String password = "mypass";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            return conn;
        } catch(Exception e){System.out.println(e);}


        return null;
        }
}

