package sqldatabaseapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;

public class DatabaseManager {

    private Connection conn = null;
    private static DatabaseManager instance = null;
    private static final boolean[] LOCK_INSTANCE = new boolean[]{};
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static final String JDBC_PREFIX = "jdbc:postgresql://";
    private static final String PARAM_USER = "user";
    private static final String PARAM_PASSWORD = "password";
    private static final String HOST_NAME = "localhost";
    private static final String DB_NAME = "postgres";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String PORT_NUMBER = "5432";

    /**
     * This method is required to implement Singleton pattern for the
     * DatabaseManager.
     */
    public static DatabaseManager getInstance() {
        if (instance != null) {
            return instance;
        }

        synchronized (LOCK_INSTANCE) {
            if (instance != null) {
                return instance;
            }

            instance = new DatabaseManager();
            return instance;
        }
    }

    /**
     * As DatabaseManager is Singleton, its constructor must be private.
     */
    private DatabaseManager() {
        super();
    }

    private void createConnection() {
        try {
            StringBuilder builder = new StringBuilder(JDBC_PREFIX);
            builder.append(HOST_NAME)
                    .append(":").append(PORT_NUMBER).append("/")
                    .append(DB_NAME)
                    .append("?").append(PARAM_USER + "=" + USER_NAME)
                    .append("&" + PARAM_PASSWORD + "=" + PASSWORD);

            Class.forName(DRIVER_NAME).newInstance();
            conn = DriverManager.getConnection(builder.toString());
            System.out.println("[DatabaseManager] Connection is created.");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState:     " + ex.getSQLState());
            System.out.println("VendorError:  " + ex.getErrorCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Closes the connection.
     */
    public void closeConnection() {
        try {
            conn.close();
            System.out.println("[DatabaseManager] Connection is closed.");
        } catch (Exception e) {
            /* ignored */ }
    }

    public boolean register(String firstname, String username, String password) {
        createConnection();
        Statement stmt = null;
        ResultSet rs = null;
        int result = 0;
        ResultSet rsLogin = null;
        ResultSet rsPass = null;
        if (firstname.isEmpty() || username.isEmpty() || password.isEmpty()) {
            return false;
        }
        String queryUsername = "Select * from  myusers where username='"
                + username + "';";
        String queryPassword = "Select * from  myusers where password='"
                + password + "';";
        try {
            PreparedStatement pstUsername = conn.prepareStatement(queryUsername);
            PreparedStatement pstPass = conn.prepareStatement(queryUsername);
            rsPass = pstPass.executeQuery();
            rsLogin = pstUsername.executeQuery();

            while (rsLogin.next() && rsPass.next()) {
                if (rsLogin.getString("username").replaceAll("\\s+", "").equals(username.replaceAll("\\s+", ""))
                        || rsPass.getString("password").replaceAll("\\s+", "").equals(password.replaceAll("\\s+", ""))) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stmt = conn.createStatement();
            String query = "Select * from  myusers where username='"
                    + username + "';";
            rs = stmt.executeQuery(query);
            if (!rs.next()) {
                String q = "Insert into myusers (firstname,username,password) values (" + "'" + firstname + "',"
                        + "'" + username + "'" + "," + "'" + password + "'" + ");";
                result = stmt.executeUpdate(q);
            }
        } catch (Exception e) {
            System.out.println("Execution failed!");
            e.printStackTrace();
            return false;
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        closeConnection();
        return true;
    }

    public boolean login(String username, String password) {
        createConnection();
        Statement stmt = null;
        ResultSet rsLogin = null;
        ResultSet rsPass = null;
        try {
            stmt = conn.createStatement();
            String queryUsername = "Select * from  myusers where username='"
                    + username + "';";
            String queryPassword = "Select * from  myusers where password='"
                    + password + "';";
            PreparedStatement pstUsername = conn.prepareStatement(queryUsername);
            PreparedStatement pstPass = conn.prepareStatement(queryUsername);
            rsPass = pstPass.executeQuery();
            rsLogin = pstUsername.executeQuery();
            while (rsLogin.next() && rsPass.next()) {
                if (rsLogin.getString("username").replaceAll("\\s+", "").equals(username.replaceAll("\\s+", ""))
                        || rsPass.getString("password").replaceAll("\\s+", "").equals(password.replaceAll("\\s+", ""))) {
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return true;
    }
}
