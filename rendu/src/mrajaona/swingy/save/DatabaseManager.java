package mrajaona.swingy.save;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import mrajaona.swingy.elements.characters.hero.Hero;

public class DatabaseManager {

    private static DatabaseManager manager = new DatabaseManager();
    private Dao<Hero, Long> heroDao        = null;

    private DatabaseManager() {}

    public static DatabaseManager getManager() {
        return (manager);
    }

    private final static String DATABASE_URL = "jdbc:sqlite:src/main/resources/swingy.db";
    private static JdbcPooledConnectionSource connectionSource = null;

    public void openConnection() throws SQLException {
        if (connectionSource == null)
            connectionSource = new JdbcPooledConnectionSource(DATABASE_URL);

        TableUtils.createTableIfNotExists(connectionSource, Hero.class);
        heroDao = DaoManager.createDao(connectionSource, Hero.class);
    }

    public void listHeroes() {
        // use connectionSource here
    }

    public void load() {
        // use connectionSource here
    }

    public void save(Hero hero) throws SQLException {
        Dao.CreateOrUpdateStatus status = heroDao.createOrUpdate(hero);
        System.out.println(
            "created: " + status.isCreated() + System.lineSeparator() +
            "updated: " + status.isUpdated() + System.lineSeparator() +
            "modified: " + status.getNumLinesChanged()
            );

    }

    public void delete(Hero hero) throws SQLException {
        heroDao.delete(hero);
    }

    public void closeConnection() throws IOException {
        connectionSource.close();
        connectionSource = null;
    }
}
