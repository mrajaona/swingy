package mrajaona.swingy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import mrajaona.swingy.elements.characters.hero.Hero;

/*
** Singleton
** Manages saved Hero data
*/

public class Save {

    private static Save manager     = new Save();
    private Dao<Hero, Long> heroDao = null;

    private Save() {}

    public static Save getManager() {
        return (manager);
    }

    // ----- Connect / disconnect from db

    private final static String DATABASE_URL = "jdbc:sqlite:src/main/resources/swingy.db";
    private static JdbcPooledConnectionSource connectionSource = null;

    public void openConnection() throws SQLException {
        if (connectionSource == null)
            connectionSource = new JdbcPooledConnectionSource(DATABASE_URL);

        TableUtils.createTableIfNotExists(connectionSource, Hero.class);
        heroDao = DaoManager.createDao(connectionSource, Hero.class);
    }

    public void closeConnection() throws IOException {
        connectionSource.close();
        connectionSource = null;
    }

    // ----- Edit db

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

    // ----- Find in db

    public List<Hero> listHeroes() throws SQLException {
        // For medium sized or large tables, this may load a lot of objects into memory so you should consider using the iterator() method instead.
        List<Hero> heroList = heroDao.queryForAll();
        return (heroList);
    }

    public Hero load(long id) throws SQLException {
        return (heroDao.queryForId(id));
    }

}
