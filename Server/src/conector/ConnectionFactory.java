package conector;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class ConnectionFactory {
    private static final String DRIVE = "com.mysql.jdbc.Driver";
    private static String url;
    private static String user;
    private static String password;


    public static Connection getConnction(){
        readConfigs();
        try {
            Class.forName(DRIVE);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error db connection", e);
        }
    }

    public static void closeConnection(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        try {
            if(stmt != null){
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConnection(con,stmt);
        try {
            if(rs != null){
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readConfigs(){
        Map<String,String> configs = new HashMap<String,String>();
        try {
            FileReader reader = new FileReader("dbConfig.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] config = line.split(":");
                configs.put(config[0], config[1]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = "jdbc:mysql://localhost:"+ configs.get("port") +"/" +configs.get("database");
        user = configs.get("user");
        password = configs.get("password");
    }
}