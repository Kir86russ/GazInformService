package utilDB;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectorTest {


    private Connector connector;
    private Connection connection;

    @BeforeEach
    void setUp() {
        connector = new Connector();
    }


    @Test
    void testGetConnection() throws SQLException {
        connection = connector.getConnection();
        assertNotNull(connection);
        assertFalse(connection.isClosed());
    }

    @Test
    void testDisconnect() throws SQLException {
        connection = connector.getConnection();
        connector.disconnect(connection);
        assertTrue(connection.isClosed());
    }

}