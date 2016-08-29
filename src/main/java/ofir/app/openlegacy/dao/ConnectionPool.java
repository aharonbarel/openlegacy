package ofir.app.openlegacy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public enum ConnectionPool {
    /*
    INSTANCE;

    private Set<Connection> availableConnections;
    private Set<Connection> busyConnections;

    private final String driver = "org.apache.derby.jdbc.ClientDriver";
    private final String url = "jdbc:derby://localhost:1527/openlegacyDB";

    ConnectionPool() {
        availableConnections = new HashSet<>();
        busyConnections = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                availableConnections.add(DriverManager.getConnection(url));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        while (!availableConnections.iterator().hasNext()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Connection con = availableConnections.iterator().next();
        availableConnections.remove(con);
        busyConnections.add(con);
        return con;
    }

    public void returnConnection(Connection connection) {
        availableConnections.add(connection);
        busyConnections.remove(connection);
    }
    */
}
