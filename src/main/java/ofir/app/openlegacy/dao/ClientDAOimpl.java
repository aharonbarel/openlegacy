package ofir.app.openlegacy.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ofir.app.openlegacy.pojo.Client;

public enum ClientDAOimpl implements ClientDAO{
    INSTANCE;

    ConnectionPool pool = ConnectionPool.INSTANCE;

    @Override
    public Client getClientById(int id) {
        ResultSet rs;
        Connection connection = pool.getConnection();

        Client client = null;

        String query = "SELECT * FROM APP.CLIENT WHERE ID = " + id;
        Statement statement;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                client = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            pool.returnConnection(connection);
        }
        return client;
    }

    @Override
    public List<Client> getClients() {
        ResultSet rs;
        Connection connection = pool.getConnection();
        List<Client> clients = new ArrayList<>();

        String query = "SELECT * FROM APP.CLIENT";
        Statement statement;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            pool.returnConnection(connection);
        }
        return clients;
    }

    @Override
    public Client createClient(Client client) {
        ResultSet rs;
        Connection connection = pool.getConnection();

        String query = "INSERT INTO APP.CLIENT (NAME, ADDRESS, PHONE) VALUES" + "(?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, client.getName());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getPhone());

            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                client.setId(rs.getInt(1));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            pool.returnConnection(connection);
        }
        return client;
    }

    @Override
    public Client updateClient(Client clientToUpdate) {
        Connection connection = pool.getConnection();

        String query = "UPDATE APP.CLIENT SET NAME = ?, ADDRESS = ?, PHONE = ? WHERE ID = ?";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, clientToUpdate.getName());
            statement.setString(2, clientToUpdate.getAddress());
            statement.setString(3, clientToUpdate.getPhone());
            statement.setInt(4, clientToUpdate.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            pool.returnConnection(connection);
        }
        return getClientById(clientToUpdate.getId());
    }

    @Override
    public Client deleteClient(Client client) {
        Connection connection = pool.getConnection();

        String query = "DELETE FROM APP.CLIENT WHERE ID = " + client.getId();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            pool.returnConnection(connection);
        }
        return client;
    }
}
