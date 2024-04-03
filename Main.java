import org.h2.jdbcx.JdbcConnectionPool;

import DAL.Repository;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/test", "sa", "sa");
        Connection conn = cp.getConnection();
        conn.close(); cp.dispose();
        System.out.println("Hello world!");
        Repository.INSTANCE.setDatabaseType(Repository.DatabaseType.IN_MEMORY);
    }
}