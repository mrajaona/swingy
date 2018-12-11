package mrajaona.swingy.save;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import mrajaona.swingy.elements.characters.hero.Hero;

public class DatabaseManager {

    private static DatabaseManager manager = new DatabaseManager();

    private DatabaseManager() {}

    public static DatabaseManager getManager() {
        return (manager);
    }

// jdbc:h2:mem:swingy
// sqlite:///swingy.db
    private final static String DATABASE_URL = "jdbc:sqlite:src/main/resources/swingy.db";
    private static JdbcPooledConnectionSource connectionSource = null;

    public void openConnection() throws SQLException {
        if (connectionSource == null)
            connectionSource = new JdbcPooledConnectionSource(DATABASE_URL);

        TableUtils.createTableIfNotExists(connectionSource, Hero.class);
    }

    public void doStuff() {
        // use connectionSource here
    }

    public void closeConnection() throws IOException {
        connectionSource.close();
        connectionSource = null;
    }
}
