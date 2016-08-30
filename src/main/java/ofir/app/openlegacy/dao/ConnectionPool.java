package ofir.app.openlegacy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

enum ConnectionPool {
    INSTANCE;

    private Set<Connection> availableConnections;
    private Set<Connection> busyConnections;

    private final String driver = "org.apache.derby.jdbc.ClientDriver";
    private final String url = "jdbc:derby://localhost:1527/openlegacyDB;create=true";

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

        Connection connection = getConnection();

        String query = "CREATE TABLE APP.CLIENT(id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name VARCHAR(100), address VARCHAR(100), phone VARCHAR(100))";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Table already exists!");
        } finally {
            returnConnection(connection);
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
}
